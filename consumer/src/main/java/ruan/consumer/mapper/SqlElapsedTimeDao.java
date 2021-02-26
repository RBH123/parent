package ruan.consumer.mapper;

import ruan.consumer.entity.SqlElapsedTime;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * sql耗时 Mapper 接口
 *
 * @author ruan
 * @since 2021-02-26
 */
@Mapper
public interface SqlElapsedTimeDao extends BaseMapper<SqlElapsedTime> {

}

