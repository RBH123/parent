package ruan.provider.service.impl;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheRefresh;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.alicp.jetcache.anno.CreateCache;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.math.BigInteger;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import ruan.provider.entity.Finance;
import ruan.provider.mapper.FinanceDao;
import ruan.provider.pojo.vo.FinanceVo;
import ruan.provider.service.FinanceService;

/**
 * <p>
 * 融资表 服务实现类
 * </p>
 *
 * @author ruan
 * @since 2021-01-12
 */
@Slf4j
@Service
public class FinanceServiceImpl extends ServiceImpl<FinanceDao, Finance> implements FinanceService {

    @CreateCache(expire = 5, timeUnit = TimeUnit.MINUTES)
    Cache<String, FinanceVo> financeCache;

    @Override
    @SneakyThrows
    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 500, maxDelay = 2000, multiplier = 2))
    @Cached(expire = 100, timeUnit = TimeUnit.SECONDS, cacheType = CacheType.REMOTE, key = "#financeId")
    @CacheRefresh(refresh = 10)
    public FinanceVo getFinanceById(BigInteger financeId) {
        Finance finance = this.baseMapper.getFinanceById(financeId);
        if (finance == null) {
            return null;
        }
        FinanceVo vo = new FinanceVo();
        BeanUtils.copyProperties(finance, vo);
        return vo;
    }
}
