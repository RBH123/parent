package ruan.gateway.config;


import java.util.Optional;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ruan.gateway.common.ResultEnum;
import ruan.gateway.common.ServerException;

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
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        Optional.ofNullable(userDetails).orElseThrow(() -> new ServerException(ResultEnum.PARAM_ERROR.getCode(), ResultEnum.PARAM_ERROR.getMessage()));
        if (StringUtils.isNotBlank(password) && !password.equals(userDetails.getPassword())) {
            throw new ServerException(ResultEnum.PASSWORD_ERROR.getCode(),
                    ResultEnum.PASSWORD_ERROR.getMessage());
        }
        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.equals(aClass);
    }
}
