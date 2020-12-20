package com.project.seller_service.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.project.seller_service.security.JwtConfig;

import java.util.Map;

@Component
public class TokenUtils {

    @Autowired
    private JwtConfig jwtConfig;


    private Map<String,Object> getTokenClaims(String token){

        Claims claims = Jwts.parser()
                .setSigningKey(jwtConfig.getSecret().getBytes())
                .parseClaimsJws(token)
                .getBody();

        return claims;
    }
}
