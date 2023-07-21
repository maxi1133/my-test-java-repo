package com.zaka.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("elastic-search")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElasticSearchConfig {

    private String host;

    private Integer port;

}
