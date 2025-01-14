package com.chatter.authservice.service;

import com.chatter.authservice.model.LoginRequest;
import com.chatter.authservice.model.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void registerUser(User user) {

    }

    @Override
    public String authenticateUser(LoginRequest loginRequest) {
        return "";
    }

    public void cacheToken(String username, String token) {
        // Сохраняем токен в Redis с временем жизни 1 час
        redisTemplate.opsForValue().set("token:" + username, token, 1, TimeUnit.HOURS);
    }

    public String getToken(String username) {
        return (String) redisTemplate.opsForValue().get("token:" + username);
    }
}
