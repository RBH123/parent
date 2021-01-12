package ruan.provider.service;

import java.math.BigInteger;
import ruan.provider.entity.Finance;
import com.baomidou.mybatisplus.extension.service.IService;
import ruan.provider.pojo.vo.FinanceVo;

/**
 * <p>
 * 融资表 服务类
 * </p>
 *
 * @author ruan
 * @since 2021-01-12
 */
public interface FinanceService extends IService<Finance> {

    FinanceVo getFinanceById(BigInteger financeId);
}
