package ruan.gateway.config;

import ch.qos.logback.classic.helpers.MDCInsertingServletFilter;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Author: rocky
 * Date: 2021/2/4 14:39
 * Project: parent1
 * Package: ruan.gateway.config
 */
public class MDCFilter extends MDCInsertingServletFilter {

    @Override
    @SneakyThrows
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String traceId = req.getHeader("traceId");
        if (StringUtils.isBlank(traceId)) {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            MDC.put("traceId", uuid);
        }
        super.doFilter(req, resp, chain);
    }
}
