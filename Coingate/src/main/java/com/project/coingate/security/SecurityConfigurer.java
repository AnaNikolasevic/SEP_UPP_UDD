package com.project.coingate.security;

import com.project.coingate.security.JwtConfig;
import com.project.coingate.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private JwtUtils jwtUtils;

    //definisemo za koje putanje vazi ili ne vazi security
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()

                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .addFilterAfter(new JwtTokenAuthenticationFilter(jwtConfig, jwtUtils), UsernamePasswordAuthenticationFilter.class)

                .authorizeRequests()
                .antMatchers("/success/**").permitAll()
                .antMatchers("/success/*/*").permitAll()
                .antMatchers("/success/*").permitAll()
                .antMatchers("/success*").permitAll()
                .antMatchers("/tokens*").permitAll()
                .antMatchers("/pay*").permitAll()
                .antMatchers("/payments*").permitAll()
                .antMatchers("/cancel*").permitAll()
                .antMatchers("/cancel/*").permitAll()
                .antMatchers("/error*").permitAll()
                .antMatchers("/confir*").permitAll()
                .antMatchers("/paymentOrderAmount").permitAll()
                .antMatchers("/getRegistrationForm").permitAll()
                .antMatchers("/registration*").permitAll()
                .antMatchers("/subscriptionDetails").permitAll()
                .antMatchers("/executeSubscription/*/*").permitAll()
                .antMatchers("/cancelSubscription/*").permitAll()
                .antMatchers("/subscriptionDetails/**").permitAll()
                .antMatchers("/ncApi/createSubscription").permitAll()
                .antMatchers("/boot*").permitAll()
                .antMatchers("/jquery*").permitAll()
                .antMatchers("/seller*").permitAll()
                .anyRequest().authenticated();
                //.anyRequest().permitAll();

    }
}
