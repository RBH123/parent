package ruan.gateway.security;


import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import ruan.gateway.entity.Users;
import ruan.gateway.util.JwtUtils;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtAuthenticionFilter extends OncePerRequestFilter {

    private JwtUtils jwtUtils;

    public JwtAuthenticionFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    @SneakyThrows
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
        String authenticion = request.getHeader("Authenticion");
        if (StringUtils.isNotBlank(authenticion)) {
            Users user = jwtUtils.parseToken(authenticion);
            if (user != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                //当authorities不为空，isAuthenticated为true，则不进行provider中的校验
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, user.getPassword(), Lists.newArrayList());
                authenticationToken.setDetails(user);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        chain.doFilter(request, response);
    }
}
