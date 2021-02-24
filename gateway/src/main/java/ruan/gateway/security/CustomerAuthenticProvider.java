package ruan.gateway.security;


import java.util.Optional;

import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import ruan.gateway.common.CustomAuthenticationException;
import ruan.gateway.common.ResultEnum;
import ruan.gateway.constant.UserStatusEnum;
import ruan.gateway.entity.UserInfo;

@Slf4j
public class CustomerAuthenticProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    public CustomerAuthenticProvider(UserDetailsService userDetailsService) {
        super();
        this.userDetailsService = userDetailsService;
    }

    @Override
    @SneakyThrows
    public Authentication authenticate(Authentication authentication) {
        UserInfo details = (UserInfo) authentication.getPrincipal();
        if(details != null && authentication.isAuthenticated()){
            return new UsernamePasswordAuthenticationToken(details, null, Lists.newArrayList());
        }
        String username = details.getUsername();
        String password = details.getPassword();
        UserInfo userInfo = (UserInfo) userDetailsService.loadUserByUsername(username);
        Optional.ofNullable(userInfo).orElseThrow(() -> new CustomAuthenticationException(ResultEnum.ACCOUNT_NOT_FOUND.getCode(), ResultEnum.ACCOUNT_NOT_FOUND.getMessage()));
        if (UserStatusEnum.LOCK.getCode().equals(userInfo.getStatus())) {
            throw new CustomAuthenticationException(ResultEnum.ACCOUNT_LOCK.getCode(), ResultEnum.ACCOUNT_LOCK.getMessage());
        }
        if (UserStatusEnum.FORBIDDEN.getCode().equals(userInfo.getStatus())) {
            throw new CustomAuthenticationException(ResultEnum.ACCOUNT_FORBIDDEN.getCode(), ResultEnum.ACCOUNT_FORBIDDEN.getMessage());
        }
        if (StringUtils.isNotBlank(password) && !password.equals(userInfo.getPassword())) {
            throw new CustomAuthenticationException(ResultEnum.PASSWORD_ERROR.getCode(), ResultEnum.PASSWORD_ERROR.getMessage());
        }
        return new UsernamePasswordAuthenticationToken(userInfo, userInfo.getPassword(), userInfo.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.equals(aClass);
    }
}
