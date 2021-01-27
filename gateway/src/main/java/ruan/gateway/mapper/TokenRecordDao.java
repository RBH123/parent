package ruan.gateway.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import ruan.gateway.entity.TokenRecord;

/**
 * Mapper 接口
 *
 * @author ruan
 * @since 2021-01-27
 */
@Mapper
public interface TokenRecordDao extends BaseMapper<TokenRecord> {

    void addTokenRecord(TokenRecord tokenRecord);
}

