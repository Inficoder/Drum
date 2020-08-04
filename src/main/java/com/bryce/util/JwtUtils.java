package com.bryce.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

@ConfigurationProperties("jwt")
@Component
@Setter
@Getter
public class JwtUtils {

    private Long ttl;
    private String key;

    public String createJwt(String id, String subject, String roles) {
        //时间戳
        long nowMills = System.currentTimeMillis();
        Date now = new Date(nowMills);
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(id)
                .setSubject(subject)
                .setIssuedAt(now)
                .claim("roles", roles)
                //设置加密方式，和盐 。盐自己规定
                .signWith(SignatureAlgorithm.HS256, key);
        if (ttl > 0) {
            jwtBuilder.setExpiration(new Date(nowMills + ttl));
        }
        return jwtBuilder.compact();
    }

    public Claims parseJWT(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }
}
