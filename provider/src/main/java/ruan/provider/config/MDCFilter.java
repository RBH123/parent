package ruan.provider.config;

import ch.qos.logback.classic.helpers.MDCInsertingServletFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import ruan.provider.util.SnowflakesUtil;

public class MDCFilter extends MDCInsertingServletFilter {

    @Override
    @SneakyThrows
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        if (!(request instanceof HttpServletRequest)) {
            return;
        }
        HttpServletRequest req = (HttpServletRequest) request;
        MDC.put("remoteHost", req.getRemoteHost());
        MDC.put("requestURI", req.getRequestURI());
        MDC.put("queryString", req.getQueryString());
        MDC.put("requestURL", req.getRequestURL().toString());
        MDC.put("method", req.getMethod());
        String traceId = req.getHeader("trace_id");
        if (StringUtils.isBlank(traceId)) {
            traceId = String.valueOf(SnowflakesUtil.INSTANCE().nextId());
        }
        MDC.put("traceId", traceId);
        try {
            super.doFilter(request, response, chain);
        } finally {
            MDC.remove("remoteHost");
            MDC.remove("requestURI");
            MDC.remove("queryString");
            MDC.remove("requestURL");
            MDC.remove("method");
            MDC.remove("traceId");
        }
    }
}
