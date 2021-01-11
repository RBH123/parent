package ruan.provider.service;

import java.math.BigInteger;
import ruan.provider.entity.OrderDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import ruan.provider.pojo.vo.OrderDetailVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ruan
 * @since 2020-10-20
 */
public interface OrderDetailService extends IService<OrderDetail> {

    OrderDetailVo detail(BigInteger detailId);
}
