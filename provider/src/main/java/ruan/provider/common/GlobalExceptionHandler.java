package ruan.provider.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Component
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServerException.class)
    public CommonResult customException(ServerException e) {
        return CommonResult.FAIL(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public CommonResult systemException(Exception e) {
        log.error("", e);
        return CommonResult.FAIL();
    }
}
