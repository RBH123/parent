package ruan.gateway.security;


import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import ruan.gateway.common.CustomAuthenticationException;
import ruan.gateway.common.ResultEnum;
import ruan.gateway.constant.GlobalEnum;
import ruan.gateway.entity.Users;
import ruan.gateway.mapper.TokenRecordDao;
import ruan.gateway.service.TokenRecordService;
import ruan.gateway.util.BeanUtil;
import ruan.gateway.util.JwtUtils;
import ruan.provider.pojo.vo.TokenRecordVo;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtAuthenticionFilter extends OncePerRequestFilter {

    private JwtUtils jwtUtils;
    private TokenRecordService tokenRecordService;

    public JwtAuthenticionFilter(JwtUtils jwtUtils,TokenRecordService tokenRecordService) {
        this.jwtUtils = jwtUtils;
        this.tokenRecordService = tokenRecordService;
    }

    @Override
    @SneakyThrows
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
        String authenticion = request.getHeader("Authenticion");
        RedisTemplate redisTemplate = BeanUtil.getBean("redisTemplate", RedisTemplate.class);
        if (StringUtils.isNotBlank(authenticion)) {
            boolean expired = jwtUtils.isExpired(authenticion);
            Users user = jwtUtils.parseToken(authenticion);
            if (expired) {
                redisTemplate.delete(user.getUserId());
                tokenRecordService.updateTokenRecord(TokenRecordVo.builder().token(authenticion).userId(user.getUserId()).status(GlobalEnum.ONE.getCode()).build());
                request.setAttribute("error_code",ResultEnum.TOKEN_OVERDUE.getCode());
                request.setAttribute("error_msg",ResultEnum.TOKEN_OVERDUE.getMessage());
                throw new CustomAuthenticationException(ResultEnum.TOKEN_OVERDUE.getCode(), ResultEnum.TOKEN_OVERDUE.getMessage());
            }
            Object token = redisTemplate.opsForValue().get(user.getUserId());
            if (token == null || (token != null && StringUtils.isBlank(token.toString()))) {
                request.setAttribute("error_code",ResultEnum.TOKEN_OVERDUE.getCode());
                request.setAttribute("error_msg",ResultEnum.TOKEN_OVERDUE.getMessage());
                throw new CustomAuthenticationException(ResultEnum.TOKEN_OVERDUE.getCode(), ResultEnum.TOKEN_OVERDUE.getMessage());
            }
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
