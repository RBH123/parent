package ruan.provider.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import ruan.provider.entity.OrderInfo;

import java.util.List;

/**
 *  Mapper 接口
 *
 * @author ruan
 * @since 2020-10-20
 */
@Mapper
public interface OrderInfoDao extends BaseMapper<OrderInfo> {

    Integer insertBatch(List<OrderInfo> orderInfoList);
}

