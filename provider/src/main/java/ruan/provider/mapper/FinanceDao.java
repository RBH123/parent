package ruan.provider.mapper;

import java.math.BigInteger;
import org.apache.ibatis.annotations.Param;
import ruan.provider.anno.DynamicDataSource;
import ruan.provider.entity.Finance;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 融资表 Mapper 接口
 *
 * @author ruan
 * @since 2021-01-12
 */
@Mapper
public interface FinanceDao extends BaseMapper<Finance> {

    @DynamicDataSource(value = "slave")
    Finance getFinanceById(@Param("financeId")BigInteger financeId);
}

