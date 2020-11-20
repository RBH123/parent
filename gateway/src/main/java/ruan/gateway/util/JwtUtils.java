package ruan.gateway.util;

import cn.hutool.core.util.ObjectUtil;
import com.google.common.collect.Lists;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import ruan.gateway.entity.JwtProperties;
import ruan.gateway.entity.UserInfo;

import javax.annotation.Resource;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

@Slf4j
@Component
public class JwtUtils {

    @Resource
    private JwtProperties jwtProperties;

    public String generateToken(Map<String,Object> tokenInfo) {
        return Jwts.builder()
                .setHeaderParam("type", "JWT")
                .setClaims(tokenInfo)
                .setIssuedAt(getCurrentDate())
                .setExpiration(getExpireDate())
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecrt())
                .setIssuer("ruan")
                .compact();
    }

    public User parseToken(String token){
        if(StringUtils.isEmpty(token)){
            return null;
        }
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(jwtProperties.getSecrt()).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        String username = claims.getOrDefault("username","").toString();
        String password = claims.getOrDefault("password", "").toString();
        User user = new User(username,password, Lists.newArrayList());
        return user;
    }

    private Date getCurrentDate(){
        return Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }

    public Date getExpireDate(){
        return Date.from(Instant.ofEpochMilli(getCurrentDate().getTime()+jwtProperties.getExpire()*1000));
    }
}
