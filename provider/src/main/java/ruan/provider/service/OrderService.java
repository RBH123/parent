package ruan.provider.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ruan.provider.entity.Order;
import ruan.provider.pojo.ao.OrderAo;

import java.util.List;

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
