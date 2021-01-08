package com.project.coingate.utils;

import com.project.coingate.dto.PaymentRequestDTO;
import com.project.coingate.security.JwtConfig;
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

// klasa koja apstrahuje sve stvari vezane za JWT
// (omogucava kreiranje novih, uzima ingormacije iz postojecih, validira)
@Component
public class  JwtUtils {

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

        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            paymentRequest.setSellerId(Long.parseLong(authentication.getPrincipal().toString()));
        }catch (Exception e){
            e.printStackTrace();
        }


        Claims claims = extractAllClaims(token);

        paymentRequest.setPrice(Float.parseFloat(claims.get("price").toString()));
        paymentRequest.setCurrency(claims.get("currency").toString());

        return paymentRequest;
    }

}
