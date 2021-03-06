package ruan.consumer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import ruan.consumer.entity.MessageRecord;

/**
 * Mapper 接口
 *
 * @author ruan
 * @since 2020-10-26
 */
@Mapper
public interface MessageRecordDao extends BaseMapper<MessageRecord> {

}

