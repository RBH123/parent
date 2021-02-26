package ruan.consumer.config;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandler;
import ruan.consumer.entity.SqlElapsedTime;
import ruan.consumer.mapper.SqlElapsedTimeDao;
import ruan.common.util.SnowflakesUtil;
import ruan.consumer.util.BeanUtil;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Author: rocky
 * Date: 2021/2/26 10:26
 * Project: parent1
 * Package: ruan.consumer.config
 */
@Slf4j
@Intercepts(@Signature(method = "query", type = Executor.class, args = {MappedStatement.class, ResultSetHandler.class, ParameterHandler.class, TypeHandler.class, RowBounds.class}))
public class CustomDbInteceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) {
        long executorBeforeTime = System.currentTimeMillis();
        try {
            invocation.proceed();
        } catch (Exception e) {
            log.info("sql执行异常");
        } finally {
            long now = System.currentTimeMillis();
            StatementHandler handler = (StatementHandler) invocation.getTarget();
            BoundSql boundSql = handler.getBoundSql();
            String sql = boundSql.getSql();
            long time = now - executorBeforeTime;
            //if (NumberUtil.sub(now, executorBeforeTime) > 1000) {
            ThreadUtil.execute(() -> {
                SqlElapsedTimeDao sqlElapsedTimeDao = BeanUtil.getBean(SqlElapsedTimeDao.class);
                SqlElapsedTime sqlElapsedTime = SqlElapsedTime.builder().id(SnowflakesUtil.INSTANCE().nextId()).sql(sql).elapsedTime(Integer.parseInt(String.valueOf(time))).createdTime(Timestamp.valueOf(LocalDateTime.now())).updatedTime(Timestamp.valueOf(LocalDateTime.now())).build();
                sqlElapsedTimeDao.insert(sqlElapsedTime);
            });
            //}
        }
        return null;
    }
}
