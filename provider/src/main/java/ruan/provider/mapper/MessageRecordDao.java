package ruan.provider.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import ruan.provider.entity.MessageRecord;

/**
 *  Mapper 接口
 *
 * @author ruan
 * @since 2020-10-26
 */
@Mapper
public interface MessageRecordDao extends BaseMapper<MessageRecord> {

}

