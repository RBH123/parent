package ruan.gateway.service;

import ruan.gateway.entity.TokenRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import ruan.provider.pojo.vo.TokenRecordVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ruan
 * @since 2021-01-27
 */
public interface TokenRecordService extends IService<TokenRecord> {
    void addTokenRecord(TokenRecordVo vo);
    void updateTokenRecord(TokenRecordVo vo);
}
