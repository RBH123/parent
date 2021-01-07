package ruan.gateway.config;

import com.google.common.collect.Maps;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ruan.gateway.util.JwtUtils;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
public class LoginAuthentication extends UsernamePasswordAuthenticationFilter {

    private JwtUtils jwtUtils;

    LoginAuthentication(AuthenticationManager manager, JwtUtils jwtUtils) {
        super.setAuthenticationManager(manager);
        this.jwtUtils = jwtUtils;
        super.setFilterProcessesUrl("/login");
    }

    @Override
    @SneakyThrows
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String requestURI = request.getRequestURI();
        log.info("请求地址:{}", requestURI);
        String token = request.getHeader("token");
        User user = jwtUtils.parseToken(token);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        super.setDetails(request, usernamePasswordAuthenticationToken);
        return super.getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
    }

    @Override
    @SneakyThrows
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        User principal = (User) authResult.getPrincipal();
        Map<String, Object> map = Maps.newHashMap();
        map.put("username", principal.getUsername());
        map.put("password", principal.getPassword());
        String token = jwtUtils.generateToken(map);
        Map<String, Object> result = Maps.newHashMap();
        result.put("token", token);
        response.getWriter().write(CommonResult.SUCCESS(result).toJson().toString());
    }
}
