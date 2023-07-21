package com.zaka.config;

import com.auth0.jwt.algorithms.Algorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("jwt-config")
@Data
@Slf4j
public class JwtConfig {

    /**
     * Secret key
     */
    private String secretKey;

    /**
     * Expiration days
     */
    private int expiredIn;

    @Bean
    public Algorithm createAlgorithm() {
        return Algorithm.HMAC512(this.secretKey);
    }
}
