package ruan.gateway.config;


import java.util.Optional;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ruan.gateway.common.CustomAuthenticationException;
import ruan.gateway.common.ResultEnum;
import ruan.gateway.constant.UserStatusEnum;
import ruan.gateway.entity.UserInfo;

@Component
public class CustomerAuthenticProvider implements AuthenticationProvider {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    @SneakyThrows
    public Authentication authenticate(Authentication authentication) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        String username = token.getPrincipal().toString();
        String password = token.getCredentials().toString();
        UserInfo userInfo = (UserInfo) userDetailsService.loadUserByUsername(username);
        Optional.ofNullable(userInfo).orElseThrow(
                () -> new CustomAuthenticationException(ResultEnum.ACCOUNT_NOT_FOUND.getCode(),
                        ResultEnum.ACCOUNT_NOT_FOUND.getMessage()));
        if (UserStatusEnum.LOCK.getCode().equals(userInfo.getStatus())) {
            throw new CustomAuthenticationException(ResultEnum.ACCOUNT_LOCK.getCode(),
                    ResultEnum.ACCOUNT_LOCK.getMessage());
        }
        if (UserStatusEnum.FORBIDDEN.getCode().equals(userInfo.getStatus())) {
            throw new CustomAuthenticationException(ResultEnum.ACCOUNT_FORBIDDEN.getCode(),
                    ResultEnum.ACCOUNT_FORBIDDEN.getMessage());
        }
        if (StringUtils.isNotBlank(password) && !password.equals(userInfo.getPassword())) {
            throw new CustomAuthenticationException(ResultEnum.PASSWORD_ERROR.getCode(),
                    ResultEnum.PASSWORD_ERROR.getMessage());
        }
        return new UsernamePasswordAuthenticationToken(userInfo, userInfo.getPassword(),
                userInfo.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.equals(aClass);
    }
}
