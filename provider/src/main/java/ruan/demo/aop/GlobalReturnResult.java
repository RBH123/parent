package ruan.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ruan.demo.common.CommonResult;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Aspect
@Component
public class GlobalReturnResult {

    @Pointcut("execution(* ruan.demo.controller.*.*(..))")
    public void pointCut() {
    }

    @Around("pointCut()")
    public String afterAop(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String requestURI = request.getRequestURI();
        Object[] args = joinPoint.getArgs();
        log.info("{}入参:{}", requestURI, args);
        Object result = joinPoint.proceed();
        if (result != null) {
            return CommonResult.SUCCESS(result).toJson();
        }
        return CommonResult.SUCCESS().toJson();
    }
}
