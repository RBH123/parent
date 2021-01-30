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

    /**
     * 插入token记录
     *
     * @param tokenRecord
     */
    void addTokenRecord(TokenRecord tokenRecord);

    /**
     * 更新token状态
     *
     * @param record
     */
    void updateTokenRecord(TokenRecord record);
}

