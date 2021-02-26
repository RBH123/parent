package ruan.consumer.service.impl;

import ruan.consumer.entity.SqlElapsedTime;
import ruan.consumer.mapper.SqlElapsedTimeDao;
import ruan.consumer.service.SqlElapsedTimeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * sql耗时 服务实现类
 * </p>
 *
 * @author ruan
 * @since 2021-02-26
 */
@Service
public class SqlElapsedTimeServiceImpl extends ServiceImpl<SqlElapsedTimeDao, SqlElapsedTime> implements SqlElapsedTimeService {

}
