package com.project.aik_bank.utils;


import com.project.aik_bank.dto.PaymentRequestDTO;
import com.project.aik_bank.security.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils {

    @Autowired
    private JwtConfig jwtConfig;

    //iz tokena izvlaci sve informacije
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(jwtConfig.getSecret().getBytes()).parseClaimsJws(token).getBody();
    }

    //uzima iz tokena vrednost polja koje mu prosledimo
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    //izvlaci username iz tokena
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    //izvlaci expiration iz tokena
    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }


    //proverava da li je token istekao
    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    //validacija tokena
    public Boolean validateToken(String token){
        return (!isTokenExpired(token));
    }

    public PaymentRequestDTO getPaymentRequest(String token) {

        PaymentRequestDTO paymentRequest = new PaymentRequestDTO();

       /* try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            paymentRequest.setSellerId(Long.parseLong(authentication.getPrincipal().toString()));

        }catch (Exception e){
            e.printStackTrace();
        }*/

        Claims claims = extractAllClaims(token);


        paymentRequest.setId(Long.parseLong(claims.get("orderId").toString()));
        paymentRequest.setPrice(Float.parseFloat(claims.get("price").toString()));
        paymentRequest.setCurrency(claims.get("currency").toString());
        paymentRequest.setMerchant_timestamp(claims.get("merchant_timestamp").toString());
        paymentRequest.setMerchant_id(claims.get("merchant_id").toString());
        paymentRequest.setMerchant_password(claims.get("merchant_password").toString());
        paymentRequest.setSuccess_url(claims.get("success_url").toString());
        paymentRequest.setFailed_url(claims.get("failed_url").toString());
        paymentRequest.setError_url(claims.get("error_url").toString());

        return paymentRequest;
    }


}
