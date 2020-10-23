package ruan.provider.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ruan.provider.common.CommonResult;
import ruan.provider.common.ServerException;

@Slf4j
@Component
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServerException.class)
    public String customException(ServerException e) {
        return CommonResult.FAIL(e.getMessage()).toJson();
    }

    @ExceptionHandler(Exception.class)
    public String systemException(Exception e) {
        log.error("", e);
        return CommonResult.FAIL().toJson();
    }
}
