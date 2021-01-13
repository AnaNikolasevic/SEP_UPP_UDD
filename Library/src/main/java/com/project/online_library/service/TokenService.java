package com.project.online_library.service;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.online_library.dto.OrderRequestDto;
import com.project.online_library.security.JwtConfig;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

    @Autowired
    JwtConfig jwtConfig;

    public String getToken(){

        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("sellerId", 1);
        claimsMap.put("price", 30.0);
        claimsMap.put("currency", "euro");

        Long now = System.currentTimeMillis();
        String token = Jwts.builder()
                .setClaims(claimsMap)
                .setSubject(Long.toString(1))
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + jwtConfig.getExpiration() * 1000))  // in milliseconds
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret().getBytes())
                .compact();

        System.out.println(token);
        return token;
    }

	public String makeToken(OrderRequestDto orderRequestDto) {
		// TODO Auto-generated method stub
		Map<String, Object> claimsMap = new HashMap<>();
        String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        orderRequestDto.setMerchant_timestamp(timeStamp);
        claimsMap.put("sellerId", orderRequestDto.getSellerId());
        claimsMap.put("price", orderRequestDto.getPrice());
        claimsMap.put("currency", orderRequestDto.getCurrency());
        claimsMap.put("merchant_timestamp", timeStamp);


        Long now = System.currentTimeMillis();
        String token = Jwts.builder()
                .setClaims(claimsMap)
                .setSubject(Long.toString(1))
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + jwtConfig.getExpiration() * 1000))  // in milliseconds
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret().getBytes())
                .compact();

        System.out.println(token);
        return token;
	}


}
