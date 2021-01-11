package ruan.provider.service;

import com.baomidou.mybatisplus.extension.service.IService;
import java.math.BigInteger;
import ruan.provider.entity.Order;
import ruan.provider.pojo.ao.OrderAo;

import java.util.List;
import ruan.provider.pojo.vo.OrderDetailVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ruan
 * @since 2020-10-20
 */
public interface OrderService extends IService<Order> {

    boolean createOrder(List<OrderAo> aoList);
}
