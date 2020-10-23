package ruan.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import ruan.demo.entity.OrderInfo;

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

