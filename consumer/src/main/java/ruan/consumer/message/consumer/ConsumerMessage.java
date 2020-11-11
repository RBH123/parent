package ruan.consumer.message.consumer;

import cn.hutool.core.util.NumberUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import ruan.common.common.ServerException;
import ruan.common.entity.GoodsInfo;
import ruan.common.mapper.GoodsInfoDao;
import ruan.common.mapper.MessageRecordDao;
import ruan.consumer.message.config.Sink;
import ruan.consumer.pojo.MessageRecordVo;
import ruan.consumer.pojo.OrderInfoVo;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
@EnableBinding(Sink.class)
public class ConsumerMessage {

    @Autowired
    GoodsInfoDao goodsInfoDao;
    @Autowired
    MessageRecordDao messageRecordDao;

    @StreamListener(Sink.ROCKETMQ_INPUT)
    public void consumerRocketMqMessage(String msg) {
        log.info("rocketmq消费消息成功:{}", msg);
    }

    @StreamListener(Sink.KAFKAINPUT)
    public void consumerKafkaMessage(String message) {
        log.info("kafka消费消息成功:{}", message);
    }

    @StreamListener(Sink.CREATEORDER)
    public void consumerCreateOrderMessage(String message) {
        MessageRecordVo messageRecordVo = JSON.parseObject(message, MessageRecordVo.class);
        if (messageRecordVo == null || StringUtils.isEmpty(messageRecordVo.getMessage())) {
            return;
        }
        List<OrderInfoVo> orderInfoVos = JSONArray.parseArray(messageRecordVo.getMessage(), OrderInfoVo.class);
        if (CollectionUtils.isEmpty(orderInfoVos)) {
            return;
        }
        Map<Long, Long> goodsCountMap = Maps.newHashMap();
        Map<Long, List<OrderInfoVo>> orderMap = orderInfoVos.parallelStream().collect(Collectors.groupingBy(OrderInfoVo::getGoodsId));
        orderMap.keySet().parallelStream().forEach(o -> {
            long sum = orderMap.get(o).parallelStream().mapToLong(OrderInfoVo::getGoodsCount).sum();
            goodsCountMap.put(o, sum);
        });
        subtractInventory(messageRecordVo.getId(), goodsCountMap);
    }


    @Transactional(rollbackFor = Exception.class)
    @SneakyThrows
    public void subtractInventory(Long messageId, Map<Long, Long> goodsCountMap) {
        Set<Long> goodsIdList = goodsCountMap.keySet();
        List<GoodsInfo> goodsInfos = goodsInfoDao.getGoodsCountByIdList(Lists.newArrayList(goodsIdList));
        if (CollectionUtils.isEmpty(goodsInfos)) {
            throw new ServerException("库存中没有该笔订单下的商品信息！");
        }
        Map<Long, Long> goodsInventoryMap = goodsInfos.stream().collect(Collectors.toMap(GoodsInfo::getId, GoodsInfo::getInventoryCount, (o1, o2) -> o2));

        for (Long goodsId : goodsCountMap.keySet()) {
            Long inventoryCount = goodsInventoryMap.get(goodsId);
            if (inventoryCount == null) {
                throw new ServerException("库存信息不足！");
            }
            long count = NumberUtil.sub(inventoryCount, goodsCountMap.get(goodsId)).longValue();
            goodsInfoDao.updateById(GoodsInfo.builder().id(goodsId).inventoryCount(count).build());
        }
    }
}
