package ruan.gateway.common;

import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationException extends AuthenticationException {

    private Integer code;

    public CustomAuthenticationException(String msg, Throwable t) {
        super(msg, t);
        this.code = ResultEnum.SERVER_ERROR.getCode();
    }

    public CustomAuthenticationException(Integer code, String msg) {
        super(msg, null);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
