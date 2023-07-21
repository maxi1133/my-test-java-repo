package com.zaka.aspect;

import com.zaka.annotation.RequestLimit;
import jakarta.annotation.PreDestroy;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
@Slf4j
public class LimitRequestAspect {

    final private static String CLIENT_SESSION_REQUEST_KEY = "%s-request";

    @Autowired
    HttpServletRequest request;

    @Autowired
    ScheduledExecutorService taskScheduler;

    private final Map<String, Integer> requestCounts = new HashMap<>();

    @PreDestroy
    void destroyBean() {
        this.taskScheduler.shutdown();
        log.info("scheduler destroy.");
    }

    @Before("@annotation(requestLimit)")
    public void intercept(JoinPoint joinPoint, RequestLimit requestLimit) throws Exception {
        log.info("Before aop run with session id {}", this.request.getSession().getId());

        final int limit = requestLimit.requestLimit();
        final String clientRequestKey = String.format(CLIENT_SESSION_REQUEST_KEY, this.request.getSession().getId());

        if (!requestCounts.containsKey(clientRequestKey)) {
            taskScheduler.schedule(() -> requestCounts.remove(clientRequestKey), 10, TimeUnit.SECONDS);
        }

        // Get the current request count for the method
        final int requestCount = requestCounts.getOrDefault(clientRequestKey, 0);

        // Check if the request count exceeds the limit
        if (requestCount >= limit) {
            throw new Exception("Request limit exceeded for method: " + clientRequestKey);
        }

        // Increment the request count
        requestCounts.put(clientRequestKey, requestCount + 1);
    }
}
