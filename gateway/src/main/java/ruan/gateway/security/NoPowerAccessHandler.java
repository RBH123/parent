package ruan.gateway.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import ruan.gateway.common.CommonResult;
import ruan.gateway.common.ResultEnum;

/**
 * 没有权限返回
 */
@Component
public class NoPowerAccessHandler implements AccessDeniedHandler {

    @Override
    @SneakyThrows
    public void handle(HttpServletRequest httpServletRequest,
            HttpServletResponse response, AccessDeniedException e) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(CommonResult
                .FAIL(ResultEnum.NO_POWERED.getCode(), ResultEnum.NO_POWERED.getMessage())
                .toJson());
        response.getWriter().flush();
    }
}
