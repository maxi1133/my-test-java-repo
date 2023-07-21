package com.zaka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class Consumer implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Consumer.class);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}