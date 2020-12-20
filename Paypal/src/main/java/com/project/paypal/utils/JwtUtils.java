package com.project.paypal.utils;

import com.project.paypal.dto.PaymentRequestDTO;
import com.project.paypal.security.JwtConfig;
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
        return Jwts.parser().setSigningKey(jwtConfig.getSecret()).parseClaimsJws(token).getBody();
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
    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    //popraviti
    public PaymentRequestDTO getPaymentRequest(String token) {

        PaymentRequestDTO paymentRequest = new PaymentRequestDTO();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //paymentRequest.setSellerId(Long.parseLong(authentication.getPrincipal().toString()));

        Map<String,Object> claims = getTokenClaims(token);

        paymentRequest.setPrice(Float.parseFloat(claims.get("price").toString()));
        paymentRequest.setCurrency(claims.get("currency").toString());

        return paymentRequest;
    }

    private Map<String,Object> getTokenClaims(String token){

        Claims claims = Jwts.parser()
                .setSigningKey(jwtConfig.getSecret().getBytes())
                .parseClaimsJws(token)
                .getBody();

        return claims;
    }
}
