package com.zaka.service;

public interface RedisService {

    void store(final String key, final Object value);

    boolean isExisted(final String key);

}
