package ruan.gateway.security;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Slf4j
public class CustomLogoutFilter extends LogoutFilter {

    private LogoutSuccessHandler logoutSuccessHandler;
    private LogoutHandler logoutHandler;

    public CustomLogoutFilter(LogoutSuccessHandler logoutSuccessHandler,
            LogoutHandler handler) {
        super(logoutSuccessHandler, new LogoutHandler[]{handler});
        this.logoutSuccessHandler = logoutSuccessHandler;
        this.logoutHandler = handler;
    }

    public CustomLogoutFilter(String logoutSuccessUrl, LogoutHandler... handlers) {
        super(logoutSuccessUrl, handlers);
    }

    @Override
    @SneakyThrows
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        if (super.requiresLogout(request, response)) {
            this.logoutHandler.logout(request,response,new UsernamePasswordAuthenticationToken(null,null));
            this.logoutSuccessHandler.onLogoutSuccess(request,response,new UsernamePasswordAuthenticationToken(null,null));
        }else {
            chain.doFilter(req,res);
        }
    }
}
