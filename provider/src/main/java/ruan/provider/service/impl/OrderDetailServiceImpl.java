package ruan.provider.service.impl;

import ruan.provider.entity.OrderDetail;
import ruan.provider.mapper.OrderDetailDao;
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

}
