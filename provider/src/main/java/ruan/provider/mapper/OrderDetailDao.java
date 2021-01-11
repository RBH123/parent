package ruan.provider.mapper;

import java.math.BigInteger;
import org.apache.ibatis.annotations.Param;
import ruan.provider.entity.OrderDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *  Mapper 接口
 *
 * @author ruan
 * @since 2020-10-20
 */
@Mapper
public interface OrderDetailDao extends BaseMapper<OrderDetail> {

    int insertBatch(List<OrderDetail> orderDetails);

    OrderDetail getOrderDetailById(@Param("id")BigInteger id);
}

