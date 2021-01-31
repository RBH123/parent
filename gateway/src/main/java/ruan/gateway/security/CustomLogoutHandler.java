package ruan.gateway.security;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import ruan.gateway.common.CustomAuthenticationException;
import ruan.gateway.common.ResultEnum;
import ruan.gateway.constant.GlobalEnum;
import ruan.gateway.entity.Users;
import ruan.gateway.service.TokenRecordService;
import ruan.gateway.util.JwtUtils;
import ruan.provider.pojo.vo.TokenRecordVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class CustomLogoutHandler implements LogoutHandler {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private TokenRecordService tokenRecordService;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        SecurityContextHolder.clearContext();
        log.info("注销！");
        String authorization = request.getHeader("Authorization");
        if (StringUtils.isBlank(authorization)) {
            throw new CustomAuthenticationException(ResultEnum.TOKEN_PARSE_ERROR.getCode(), ResultEnum.TOKEN_PARSE_ERROR.getMessage());
        }
        Users users = jwtUtils.parseToken(authorization);
        if (users == null) {
            throw new CustomAuthenticationException(ResultEnum.TOKEN_PARSE_ERROR.getCode(), ResultEnum.TOKEN_PARSE_ERROR.getMessage());
        }
        TokenRecordVo recordVo = TokenRecordVo.builder().userId(users.getUserId()).status(GlobalEnum.ONE.getCode()).build();
        tokenRecordService.updateTokenRecord(recordVo);
    }
}
