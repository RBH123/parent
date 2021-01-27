package ruan.gateway.security;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Slf4j
public class CustomLogoutFilter extends LogoutFilter {

    public CustomLogoutFilter(LogoutSuccessHandler logoutSuccessHandler,
            LogoutHandler... handlers) {
        super(logoutSuccessHandler, handlers);
    }

    public CustomLogoutFilter(String logoutSuccessUrl, LogoutHandler... handlers) {
        super(logoutSuccessUrl, handlers);
    }

    @Override
    @SneakyThrows
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) {
        log.info("登出！");
    }
}
