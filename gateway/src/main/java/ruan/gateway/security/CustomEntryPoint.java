package ruan.gateway.security;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
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
        response.setCharacterEncoding("UTF-8");
        Object errorCode = request.getAttribute("error_code");
        Object errorMsg = request.getAttribute("error_msg");
        if(errorCode != null && errorMsg != null){
            response.getWriter().println(CommonResult.FAIL(Integer.valueOf(errorCode.toString()), errorMsg.toString()).toJson());
        }else {
            response.getWriter().println(CommonResult.FAIL(ResultEnum.AUTHENTICATION_FAILED.getCode(), ResultEnum.AUTHENTICATION_FAILED.getMessage()).toJson());
        }
        response.getWriter().flush();
    }
}
