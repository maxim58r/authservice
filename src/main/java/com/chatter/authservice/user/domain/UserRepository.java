package com.chatter.authservice.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.core.RedisHash;

import java.util.Optional;

@RedisHash
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

}
