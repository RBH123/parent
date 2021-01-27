package ruan.gateway.security;


import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import ruan.gateway.entity.Users;
import ruan.gateway.util.JwtUtils;

public class JwtAuthenticionFilter extends OncePerRequestFilter {

    private JwtUtils jwtUtils;

    public JwtAuthenticionFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    @SneakyThrows
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain chain) {
        String authenticion = request.getHeader("Authenticion");
        if (StringUtils.isNotBlank(authenticion)) {
            Users user = jwtUtils.parseToken(authenticion);
            if (user != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        user.getUsername(), user.getPassword());
                authenticationToken.setDetails(user);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        chain.doFilter(request, response);
    }
}
