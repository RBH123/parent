package ruan.gateway.config;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import ruan.gateway.common.CommonResult;
import ruan.gateway.common.ResultEnum;

@Component
public class CustomEntryPoint implements AuthenticationEntryPoint {

    @Override
    @SneakyThrows
    public void commence(HttpServletRequest request,
            HttpServletResponse response, AuthenticationException e) {
        response.setContentType("application/json");
        String code = request.getAttribute("code") != null ? request.getAttribute("code").toString()
                : ResultEnum.SERVER_ERROR.getCode().toString();
        String message =
                request.getAttribute("msg") != null ? request.getAttribute("msg").toString()
                        : ResultEnum.SERVER_ERROR.getMessage();
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(CommonResult.FAIL(Integer.parseInt(code), message).toJson());
        response.getWriter().flush();
    }
}
