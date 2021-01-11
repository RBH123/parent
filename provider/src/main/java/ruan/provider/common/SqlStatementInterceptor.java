package ruan.provider.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;

import java.sql.Statement;
import java.util.Properties;

/**
 * Author: rocky
 * Date: 2020/11/27 17:58
 * Project: parent
 * Package: ruan.provider.common
 *自定义拦截器拦截sql，输出打印时间
 */
@Slf4j
@Intercepts(@Signature(method = "query", type = StatementHandler.class, args = {Statement.class, ResultHandler.class}))
public class SqlStatementInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        long startTime = System.currentTimeMillis();
        try {
            return invocation.proceed();
        } catch (Exception e) {
            log.error("", e);
        } finally {
            long endTime = System.currentTimeMillis();
            StatementHandler handler = (StatementHandler) invocation.getTarget();
            String sql = handler.getBoundSql().getSql();
            log.info("sql：{}执行时间：{}ms", sql, endTime - startTime);
        }
        return null;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
