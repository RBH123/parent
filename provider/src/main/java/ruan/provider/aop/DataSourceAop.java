package ruan.provider.aop;

import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ruan.provider.anno.DynamicDataSource;
import ruan.provider.common.DynamicDataSourceContextHolder;


@Order(-1)
@Slf4j
@Aspect
@Component
public class DataSourceAop {

    @Pointcut("@annotation(ruan.provider.anno.DynamicDataSource)")
    public void pointCut(){}

    /**
     * 设置动态数据源
     * @param point
     */
    @Before("pointCut()")
    public void switchDataSource(JoinPoint point){
        MethodSignature signature = (MethodSignature) point.getSignature();
        DynamicDataSource annotation = signature.getMethod().getAnnotation(DynamicDataSource.class);
        if(annotation == null){
            return;
        }
        String value = annotation.value();
        DynamicDataSourceContextHolder.setDataSource(value);
    }

    /**
     * 清空数据源
     * @param point
     */
    @After("pointCut()")
    public void clearDataSource(JoinPoint point){
        MethodSignature signature = (MethodSignature) point.getSignature();
        DynamicDataSource annotation = signature.getMethod().getAnnotation(DynamicDataSource.class);
        if(annotation == null){
            return;
        }
        DynamicDataSourceContextHolder.emptyDataSource();
    }
}
