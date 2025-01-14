package com.chatter.authservice.repository;

import com.chatter.authservice.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testRedisOperations() {
        User user = new User();
        user.setId("1");
        user.setUsername("testuser");
        user.setEmail("test@example.com");

        // Сохранение
        userRepository.save(user);

        // Извлечение
        Optional<User> retrievedUser = userRepository.findById("1");
        Assertions.assertTrue(retrievedUser.isPresent());
        Assertions.assertEquals("testuser", retrievedUser.get().getUsername());
    }
}

