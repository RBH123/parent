package ruan.provider.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ruan.provider.entity.OrderInfo;
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
public interface OrderInfoService extends IService<OrderInfo> {

    boolean createOrder(List<OrderAo> aoList);
}
