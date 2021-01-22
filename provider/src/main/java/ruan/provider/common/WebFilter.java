package ruan.provider.common;

import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebFilter implements Filter {

    @Override
    @SneakyThrows
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI = request.getRequestURI();
        log.info("请求地址为:{}",requestURI);
        Map<String, String[]> parameterMap = request.getParameterMap();
        log.info("请求参数为:{}",parameterMap);
        filterChain.doFilter(request,servletResponse);
    }
}
