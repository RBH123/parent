package ruan.provider.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Maps;
import java.math.BigInteger;
import java.util.Map;
import org.springframework.beans.BeanUtils;
import ruan.provider.entity.OrderDetail;
import ruan.provider.mapper.OrderDetailDao;
import ruan.provider.pojo.vo.OrderDetailVo;
import ruan.provider.service.OrderDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ruan
 * @since 2020-10-20
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailDao, OrderDetail> implements OrderDetailService {

    @Override
    public OrderDetailVo detail(BigInteger detailId) {
        OrderDetail detail = this.baseMapper.getOrderDetailById(detailId);
        if(detail == null){
            return null;
        }
        OrderDetailVo vo = new OrderDetailVo();
        BeanUtils.copyProperties(detail,vo);
        return vo;
    }
}
