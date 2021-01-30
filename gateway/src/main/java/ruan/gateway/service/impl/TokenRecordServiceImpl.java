package ruan.gateway.service.impl;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ruan.gateway.common.ResultEnum;
import ruan.gateway.common.ServerException;
import ruan.gateway.entity.TokenRecord;
import ruan.gateway.mapper.TokenRecordDao;
import ruan.gateway.service.TokenRecordService;
import ruan.gateway.util.SnowflakesUtil;
import ruan.provider.pojo.vo.TokenRecordVo;

import java.math.BigInteger;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ruan
 * @since 2021-01-27
 */
@Service
public class TokenRecordServiceImpl extends ServiceImpl<TokenRecordDao, TokenRecord> implements
        TokenRecordService {

    @Autowired
    private TokenRecordDao tokenRecordDao;

    @CreateCache(expire = 3, timeUnit = TimeUnit.HOURS, name = "userId", cacheType = CacheType.REMOTE)
    private Cache<BigInteger, String> tokenCache;

    @Override
    @SneakyThrows
    public void addTokenRecord(TokenRecordVo vo) {
        Optional.ofNullable(vo).map(t -> t.getUserId())
                .orElseThrow(() -> new ServerException(ResultEnum.PARAM_ERROR
                        .getCode(), ResultEnum.PARAM_ERROR.getMessage()));
        if (StringUtils.isEmpty(vo.getToken())) {
            return;
        }
        QueryWrapper<TokenRecord> wrapper = new QueryWrapper();
        wrapper.eq("status", 0);
        wrapper.eq("user_id", vo.getUserId());
        TokenRecord tokenRecord = tokenRecordDao.selectOne(wrapper);
        if (tokenRecord == null) {
            tokenRecordDao.insert(TokenRecord.builder().recordId(SnowflakesUtil.INSTANCE().nextId()).userId(vo.getUserId()).token(vo.getToken()).build());
        } else {
            tokenRecord.setToken(vo.getToken());
            tokenRecordDao.updateById(tokenRecord);
        }
        tokenCache.put(vo.getUserId(), vo.getToken());
    }

    @Override
    @SneakyThrows
    public void updateTokenRecord(TokenRecordVo vo) {
        Optional.ofNullable(vo).orElseThrow(
                () -> new ServerException(ResultEnum.PARAM_ERROR.getCode(),
                        ResultEnum.PARAM_ERROR.getMessage()));
        TokenRecord record = new TokenRecord();
        BeanUtils.copyProperties(vo, record);
        tokenRecordDao.updateTokenRecord(record);
    }
}
