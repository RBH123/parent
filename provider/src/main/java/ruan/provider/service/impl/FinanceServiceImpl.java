package ruan.provider.service.impl;

import java.math.BigInteger;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.Interceptor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.interceptor.RetryInterceptorBuilder;
import org.springframework.retry.interceptor.RetryInterceptorBuilder.StatefulRetryInterceptorBuilder;
import ruan.provider.common.RetryInterceptor;
import ruan.provider.entity.Finance;
import ruan.provider.mapper.FinanceDao;
import ruan.provider.pojo.vo.FinanceVo;
import ruan.provider.service.FinanceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

    int cnt = 0;

    @Override
    @SneakyThrows
    @Retryable(value = Exception.class,maxAttempts = 3,backoff = @Backoff(delay = 500,maxDelay =
            2000,multiplier = 2))
    public FinanceVo getFinanceById(BigInteger financeId){
        Finance finance = this.baseMapper.getFinanceById(financeId);
        cnt++;
        log.info("重试次数:{}",cnt);
        throw new Exception();
//        if(finance == null){
//            return null;
//        }
//        FinanceVo vo = new FinanceVo();
//        BeanUtils.copyProperties(finance,vo);
//        return vo;
    }
}
