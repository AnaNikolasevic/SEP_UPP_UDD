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
        claimsMap.put("orderId", orderRequestDto.getId());
        claimsMap.put("sellerId", orderRequestDto.getSellerId());
        claimsMap.put("price", orderRequestDto.getPrice());
        claimsMap.put("currency", orderRequestDto.getCurrency());
        claimsMap.put("merchant_timestamp", orderRequestDto.getMerchant_timestamp());
        claimsMap.put("merchant_id", orderRequestDto.getMerchant_id());
        claimsMap.put("merchant_password", orderRequestDto.getMerchant_password());
        claimsMap.put("success_url", orderRequestDto.getSuccess_url());
        claimsMap.put("failed_url", orderRequestDto.getFailed_url());
        claimsMap.put("error_url", orderRequestDto.getError_url());

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
