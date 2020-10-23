package ruan.demo.service.impl;

import ruan.demo.entity.OrderDetail;
import ruan.demo.mapper.OrderDetailDao;
import ruan.demo.service.OrderDetailService;
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
