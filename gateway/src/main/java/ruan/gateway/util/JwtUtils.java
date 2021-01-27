package ruan.gateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import javax.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import ruan.gateway.common.ResultEnum;
import ruan.gateway.common.ServerException;
import ruan.gateway.entity.JwtProperties;
import ruan.gateway.entity.Users;

@Slf4j
@Component
public class JwtUtils {

    @Resource
    private JwtProperties jwtProperties;

    public String generateToken(Map<String, Object> tokenInfo) {
        return Jwts.builder()
                .setClaims(tokenInfo)
                .setIssuedAt(getCurrentDate())
                .setExpiration(getExpireDate())
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecrt())
                .setIssuer("ruan")
                .compact();
    }

    @SneakyThrows
    public Users parseToken(String token) {
        Users user = null;
        try {
            Claims claims = getClaims(token);
            user = ObjectUtil.mapToObject(claims, Users.class);
        } catch (Exception e) {
            log.error("token解析异常:{}", e);
            throw new ServerException(ResultEnum.TOKEN_OVERDUE.getCode(),
                    ResultEnum.TOKEN_OVERDUE.getMessage());
        }
        return user;
    }

    private Date getCurrentDate() {
        return Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }

    public Date getExpireDate() {
        return new Date(System.currentTimeMillis() + jwtProperties.getExpire() * 1000);
    }

    public Claims getClaims(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        return Jwts.parser().setSigningKey(jwtProperties.getSecrt()).parseClaimsJws(token)
                .getBody();
    }

    public boolean isExpired(String token) {
        Claims claims = getClaims(token);
        Date expiration = claims.getExpiration();
        return expiration.before(new Date());
    }

    public String refreshToken(String token) {
        Claims claims = getClaims(token);
        return generateToken(claims);
    }
}
