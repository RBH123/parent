package ruan.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ruan.demo.constant.RedisKeyConstant;
import ruan.demo.entity.MessageRecord;
import ruan.demo.entity.OrderDetail;
import ruan.demo.entity.OrderInfo;
import ruan.demo.kafka.product.KafkaProduct;
import ruan.demo.mapper.MessageRecordDao;
import ruan.demo.mapper.OrderDetailDao;
import ruan.demo.mapper.OrderInfoDao;
import ruan.demo.pojo.ao.OrderAo;
import ruan.demo.pojo.ao.OrderInfoAo;
import ruan.demo.service.OrderInfoService;
import ruan.demo.util.SnowflakesUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ruan
 * @since 2020-10-20
 */
@Slf4j
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoDao, OrderInfo> implements OrderInfoService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private OrderInfoDao orderInfoDao;
    @Autowired
    private OrderDetailDao orderDetailDao;
    @Autowired
    private MessageRecordDao messageRecordDao;
    @Autowired
    private KafkaProduct kafkaProduct;
    @Autowired
    private ZookeeperDistributedLockImpl zookeeperDistributedLock;

    @Override
    @SneakyThrows
    public boolean createOrder(List<OrderAo> aoList) {
        List<OrderInfoAo> orderInfoAos = new ArrayList<>();
        aoList.parallelStream().forEach(ao -> orderInfoAos.addAll(ao.getOrderInfoAoList()));
        Map<Long, List<Long>> listMap = aoList.parallelStream().collect(Collectors.toMap(OrderAo::getStoreId, ao -> ao.getOrderInfoAoList().parallelStream().map(a -> a.getGoodsId()).collect(Collectors.toList())));
        for (OrderInfoAo order : orderInfoAos) {
            Long goodsId = order.getGoodsId();
            String nodePath = zookeeperDistributedLock.tryLock(goodsId.toString());
            if (StringUtils.hasText(nodePath)) {
                String redisKey = RedisKeyConstant.INVENTORY_COUNT.concat(goodsId.toString());
                Long increment = redisTemplate.opsForValue().increment(redisKey, -1);
                if (increment == null) {
                    throw new Exception("创建订单失败，从redis获取商品库存数据异常！");
                }
                if (increment < 1) {
                    throw new Exception("创建订单失败，库存容量不足！");
                }
            }
            listMap.keySet().parallelStream().forEach(i -> {
                if (listMap.get(i).contains(order.getGoodsId())) {
                    order.setStoreId(i);
                }
            });
        }
        MessageRecord messageRecord = createGoodsOrder(orderInfoAos);
        kafkaProduct.sendMessage(JSON.toJSONString(messageRecord));
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public MessageRecord createGoodsOrder(List<OrderInfoAo> orderInfoAos) {
        Map<Long, List<OrderInfoAo>> orderMap = orderInfoAos.parallelStream().collect(Collectors.groupingBy(OrderInfoAo::getStoreId));
        List<OrderInfo> orderInfoList = new ArrayList<>();
        orderMap.values().parallelStream().forEach(v -> {
            long orderId = SnowflakesUtil.INSTANCE().nextId();
            List<OrderInfo> orderInfos = v.parallelStream().map(v1 -> {
                OrderInfo orderInfo = new OrderInfo();
                BeanUtils.copyProperties(v1, orderInfo);
                orderInfo.setOrderId(orderId);
                orderInfo.setId(SnowflakesUtil.INSTANCE().nextId());
                return orderInfo;
            }).collect(Collectors.toList());
            orderInfoList.addAll(orderInfos);
        });
        Map<Long, Long> orderIdMap = orderInfoList.parallelStream().collect(Collectors.toMap(OrderInfo::getStoreId, OrderInfo::getOrderId));
        orderInfoDao.insertBatch(orderInfoList);
        List<OrderDetail> orderDetailList = orderMap.values().parallelStream().map(value -> {
            long totalCount = value.parallelStream().mapToLong(OrderInfoAo::getGoodsCount).sum();
            BigDecimal totalAmount = value.parallelStream().map(OrderInfoAo::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
            Long orderId = orderIdMap.get(value.get(0).getStoreId());
            OrderDetail orderDetail = OrderDetail.builder().id(orderId).totalAmount(totalAmount).goodsCount(totalCount).build();
            return orderDetail;
        }).collect(Collectors.toList());
        orderDetailDao.insertBatch(orderDetailList);
        MessageRecord messageRecord = MessageRecord.builder().id(SnowflakesUtil.INSTANCE().nextId()).message(JSON.toJSONString(orderInfoAos)).status(0).build();
        messageRecordDao.insert(messageRecord);
        return messageRecord;
    }
}
