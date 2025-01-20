package com.chatter.authservice.service;

import com.chatter.authservice.config.adapter.RedisTemplateAdapter;
import com.chatter.authservice.model.LoginRequest;
import com.chatter.authservice.entity.User;
import com.chatter.authservice.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RedisTemplateAdapter redisTemplateAdapter;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpirationMs;


    public UserServiceImpl(UserRepository userRepository, RedisTemplateAdapter redisTemplateAdapter) {
        this.userRepository = userRepository;
        this.redisTemplateAdapter = redisTemplateAdapter;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }


    @Transactional
    @Override
    public void registerUser(User user) {
        log.info("Registering user {}", user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        log.info("User registered successfully");
    }

    @Transactional
    public void logout(String username) {
        redisTemplateAdapter.deleteToken("token:" + username);
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }


    @Override
    public String authenticateUser(@NotNull LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = generateJwtToken(user.getUsername());
        cacheToken(user.getUsername(), token);
        return token;
    }

    private String generateJwtToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationMs)).signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public void cacheToken(String username, String token) {
        redisTemplateAdapter.cacheToken("token:" + username, token, 1, TimeUnit.HOURS);
    }

    public String getToken(String username) {
        return (String) redisTemplateAdapter.getToken("token:" + username);
    }
}
