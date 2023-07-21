package com.zaka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
public class TaskSchedulerConfig {

    @Bean
    public ScheduledExecutorService createTaskScheduler() {
        return Executors.newScheduledThreadPool(20);
    }
}
