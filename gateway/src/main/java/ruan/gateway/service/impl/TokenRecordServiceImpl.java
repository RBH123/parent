package ruan.gateway.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.Optional;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ruan.gateway.common.ResultEnum;
import ruan.gateway.common.ServerException;
import ruan.gateway.entity.TokenRecord;
import ruan.gateway.mapper.TokenRecordDao;
import ruan.gateway.service.TokenRecordService;
import ruan.gateway.util.SnowflakesUtil;
import ruan.provider.pojo.vo.TokenRecordVo;

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

    @Override
    @SneakyThrows
    public void addTokenRecord(TokenRecordVo vo) {
        Optional.ofNullable(vo).map(t -> t.getUserId())
                .orElseThrow(() -> new ServerException(ResultEnum.PARAM_ERROR
                        .getCode(), ResultEnum.PARAM_ERROR.getMessage()));
        if (StringUtils.isEmpty(vo.getToken())) {
            return;
        }
        TokenRecord record = TokenRecord.builder().recordId(SnowflakesUtil.INSTANCE().nextId())
                .userId(vo.getUserId()).token(vo.getToken()).build();
        tokenRecordDao.addTokenRecord(record);
    }
}
