package com.zaka.service.seviceImpl;

import com.zaka.service.RedisService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RedisServiceImpl implements RedisService {

    @Autowired
    RedisTemplate redisTemplate;

    /**
     *
     */
    @PostConstruct
    private void log() {
        log.info("Redis is ready.");
    }

    /**
     *
     * @param key
     * @param value
     */
    @Override
    public void store(final String key, final Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public boolean isExisted(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }
}
