package com.project.seller_service.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.seller_service.dto.OrderRequestDto;
import com.project.seller_service.security.JwtConfig;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

    @Autowired
    JwtConfig jwtConfig;

    public String getToken(OrderRequestDto orderRequestDto){

        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("sellerId", orderRequestDto.getSellerId());
        claimsMap.put("price", orderRequestDto.getPrice());
        claimsMap.put("currency", orderRequestDto.getCurrency());

        Long now = System.currentTimeMillis();
        String token = Jwts.builder()
                .setClaims(claimsMap)
                .setSubject(Long.toString(orderRequestDto.getSellerId()))
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + jwtConfig.getExpiration() * 1000))  // in milliseconds
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret().getBytes())
                .compact();

        System.out.println(token);
        return token;
    }


}
