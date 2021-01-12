package ruan.provider.service.impl;

import java.math.BigInteger;
import org.springframework.beans.BeanUtils;
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
@Service
public class FinanceServiceImpl extends ServiceImpl<FinanceDao, Finance> implements FinanceService {

    @Override
    public FinanceVo getFinanceById(BigInteger financeId){
        Finance finance = this.baseMapper.getFinanceById(financeId);
        if(finance == null){
            return null;
        }
        FinanceVo vo = new FinanceVo();
        BeanUtils.copyProperties(finance,vo);
        return vo;
    }
}
