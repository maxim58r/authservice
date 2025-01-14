package com.chatter.authservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("User")
@Data
public class User {
    @Id
    private String id;
    private String username;
    private String email;
}

