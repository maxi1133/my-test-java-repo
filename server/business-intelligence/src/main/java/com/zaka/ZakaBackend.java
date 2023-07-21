package com.zaka;

import com.zaka.config.DatabaseConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 *
 */
@Slf4j
@SpringBootApplication
@EnableConfigurationProperties
public class ZakaBackend implements CommandLineRunner {

    @Autowired
    private DatabaseConfig databaseConfig;

//    @Autowired
//    @Qualifier("B")
//    private AService aService;

    /**
     *
     */
    public static void main(String[] args) {
        SpringApplication.run(ZakaBackend.class, args);
    }

    /**
     *
     */
    @Override
    public void run(String... args) throws Exception {
        log.info(this.databaseConfig.getUrl());
        log.info("App started !");
    }
}
