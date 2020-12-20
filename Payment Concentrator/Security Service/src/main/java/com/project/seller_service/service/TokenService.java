package com.project.seller_service.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.seller_service.security.JwtConfig;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

    @Autowired
    JwtConfig jwtConfig;

    public String getToken(){

        Integer i = 1;
        Long sellerId = new Long(i);

        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("sellerId", sellerId);
        claimsMap.put("price", 30.0);
        claimsMap.put("currency", "USD");

        Long now = System.currentTimeMillis();
        String token = Jwts.builder()
                .setClaims(claimsMap)
                .setSubject(Long.toString(sellerId))
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + jwtConfig.getExpiration() * 1000))  // in milliseconds
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret().getBytes())
                .compact();

        System.out.println(token);
        return token;
    }


}
