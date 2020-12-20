package com.project.security_service.security;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


    @Component
    public class JwtConfig {


        @Value("${security.jwt.header:Authorization}")
        private String header;

        @Value("${security.jwt.prefix:Bearer }")
        private String prefix;

        //u sekundama je
        @Value("${security.jwt.expiration:#{600000}}")
        private int expiration;

        @Value("${security.jwt.secret:JwtSecretKey}")
        private String secret;

        // In case you want to use plain getters instead of lombok.
        public String getHeader() {
            return header;
        }

        public String getPrefix() {
            return prefix;
        }

        public int getExpiration() {
            return expiration;
        }

        public String getSecret() {
            return secret;
        }

    }

