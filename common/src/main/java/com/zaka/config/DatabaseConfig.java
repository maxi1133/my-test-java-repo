package com.zaka.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@ConfigurationProperties("spring.datasource")
@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatabaseConfig {

    /**
     *
     */
    private String url;

    /**
     *
     */
    private String username;

    /**
     *
     */
    private String password;
}
