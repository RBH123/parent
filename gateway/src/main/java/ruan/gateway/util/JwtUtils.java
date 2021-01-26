package ruan.gateway.util;

import com.google.common.collect.Lists;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import ruan.gateway.entity.JwtProperties;

@Slf4j
@Component
public class JwtUtils {

    @Resource
    private JwtProperties jwtProperties;

    public String generateToken(Map<String, Object> tokenInfo) {
        return Jwts.builder()
                .setHeaderParam("type", "JWT")
                .setClaims(tokenInfo)
                .setIssuedAt(getCurrentDate())
                .setExpiration(getExpireDate())
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecrt())
                .setIssuer("ruan")
                .compact();
    }

    public User parseToken(String token) {
        User user = null;
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(jwtProperties.getSecrt())
                    .parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            String username = claims.getOrDefault("username", "").toString();
            String password = claims.getOrDefault("password", "").toString();
            user = new User(username, password, Lists.newArrayList());
        } catch (Exception e) {
            log.error("异常！");
        }
        return user;
    }

    private Date getCurrentDate() {
        return Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }

    public Date getExpireDate() {
        return Date.from(Instant
                .ofEpochMilli(getCurrentDate().getTime() + jwtProperties.getExpire() * 1000));
    }
}
