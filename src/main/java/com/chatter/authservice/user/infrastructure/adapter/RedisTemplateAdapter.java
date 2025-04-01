package com.chatter.authservice.user.infrastructure.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedisTemplateAdapter {
    private final RedisTemplate<String, Object> redisTemplate;

    public void cacheToken(String key, Object value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    public Object getToken(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void deleteToken(String key) {
        redisTemplate.delete(key);
    }
}

