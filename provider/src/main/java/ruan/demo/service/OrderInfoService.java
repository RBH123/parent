package ruan.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ruan.demo.entity.OrderInfo;
import ruan.demo.pojo.ao.OrderAo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ruan
 * @since 2020-10-20
 */
public interface OrderInfoService extends IService<OrderInfo> {

    boolean createOrder(List<OrderAo> aoList);
}
