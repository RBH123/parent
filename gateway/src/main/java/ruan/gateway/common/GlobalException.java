package ruan.gateway.common;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(value = ServerException.class)
    public Object exception(ServerException e) {
        return CommonResult.FAIL(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = CustomAuthenticationException.class)
    public Object exception(CustomAuthenticationException e) {
        return CommonResult.FAIL(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public Object exception(Exception e) {
        return CommonResult.FAIL();
    }
}
