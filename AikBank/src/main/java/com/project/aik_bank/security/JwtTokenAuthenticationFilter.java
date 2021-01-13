package com.project.aik_bank.security;

import com.project.aik_bank.utils.JwtUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
//presrece zahtev i izvlaci header
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {


    private final JwtConfig jwtConfig;
    private final JwtUtils jwtUtils;

    public JwtTokenAuthenticationFilter(JwtConfig jwtConfig, JwtUtils jwtUtils) {
        this.jwtConfig = jwtConfig;
        this.jwtUtils = jwtUtils;
    }

    //metoda koja radi posao
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String header = httpServletRequest.getHeader(jwtConfig.getHeader());


        //if(header == null || !header.startsWith(jwtConfig.getPrefix())) {
        //  filterChain.doFilter(httpServletRequest, httpServletResponse);  		// If not valid, go to the next filter.
        //   return;
        // }


        try{
            String token = header.replace(jwtConfig.getPrefix(), "");
            String username = jwtUtils.extractUsername(token);

            System.out.println(username);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                if (jwtUtils.validateToken(token)) {

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, null, null);
                    usernamePasswordAuthenticationToken.setDetails(token);
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    System.out.println(username);
                }
            }

        }catch(Exception e){
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}