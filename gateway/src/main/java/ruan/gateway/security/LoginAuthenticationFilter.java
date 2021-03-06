package ruan.gateway.security;

import com.alibaba.fastjson.JSON;
import com.alicp.jetcache.anno.CreateCache;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ruan.gateway.common.CommonResult;
import ruan.gateway.common.CustomAuthenticationException;
import ruan.gateway.common.ResultEnum;
import ruan.gateway.entity.UserInfo;
import ruan.gateway.service.TokenRecordService;
import ruan.gateway.util.BeanUtil;
import ruan.gateway.util.HttpUtil;
import ruan.gateway.util.JwtUtils;
import ruan.gateway.util.ObjectUtil;
import ruan.provider.pojo.vo.TokenRecordVo;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
public class LoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private JwtUtils jwtUtils;
    private TokenRecordService tokenRecordService;

    LoginAuthenticationFilter(AuthenticationProvider provider, JwtUtils jwtUtils, TokenRecordService tokenRecordService) {
        super();
        super.setAuthenticationManager(new ProviderManager(provider));
        this.jwtUtils = jwtUtils;
        this.tokenRecordService = tokenRecordService;
    }

    @Override
    @SneakyThrows
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) {
        String jsonBody = HttpUtil.getRequestJsonBody(request);
        //请求信息中没有获取到用户名和密码
        if (StringUtils.isBlank(jsonBody)) {
            throw new CustomAuthenticationException(
                    ResultEnum.AUTHENTICATION_PARAM_ERROR.getCode(),
                    ResultEnum.AUTHENTICATION_PARAM_ERROR.getMessage());
        }
        UserInfo userInfo = JSON.parseObject(jsonBody, UserInfo.class);
        if (StringUtils.isEmpty(userInfo.getUsername()) || StringUtils
                .isEmpty(userInfo.getPassword())) {
            throw new CustomAuthenticationException(
                    ResultEnum.AUTHENTICATION_PARAM_ERROR.getCode(),
                    ResultEnum.AUTHENTICATION_PARAM_ERROR.getMessage());
        }
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userInfo, userInfo.getPassword());
        token.setDetails(userInfo);
        super.setDetails(request, token);
        return super.getAuthenticationManager().authenticate(token);
    }

    @Override
    @SneakyThrows
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                         FilterChain chain, Authentication authResult) {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        UserInfo userInfo = (UserInfo) authResult.getPrincipal();
        Map<String, Object> map = ObjectUtil.beanToMap(
                UserInfo.builder().userId(userInfo.getUserId()).username(userInfo.getUsername())
                        .build());
        String token = jwtUtils.generateToken(map);
        TokenRecordVo vo = TokenRecordVo.builder().token(token).userId(userInfo.getUserId()).build();
        tokenRecordService.addTokenRecord(vo);
        RedisTemplate redisTemplate = BeanUtil.getBean("redisTemplate", RedisTemplate.class);
        redisTemplate.opsForValue().setIfAbsent(vo.getUserId(),token);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(CommonResult.SUCCESS(token).toJson());
    }

    @Override
    @SneakyThrows
    public void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                           AuthenticationException failed) {
        SecurityContextHolder.clearContext();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        if (failed instanceof CustomAuthenticationException) {
            CustomAuthenticationException exception = (CustomAuthenticationException) failed;
            response.getWriter().println(
                    CommonResult.FAIL(exception.getCode(), exception.getMessage()).toJson());
        } else {
            response.getWriter().println(CommonResult
                    .FAIL(ResultEnum.SERVER_ERROR.getCode(), ResultEnum.SERVER_ERROR.getMessage()).toJson());
        }
    }
}
