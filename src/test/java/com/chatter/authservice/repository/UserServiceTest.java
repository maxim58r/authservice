package com.chatter.authservice.repository;

import com.chatter.authservice.entity.User;
import com.chatter.authservice.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void testRedisOperations() {
        // Создание нового пользователя
        User user = User.builder()
                .username("testuser")
                .email("test@example.com")
                .password("password")
                .build();

        // Сохранение
        userService.registerUser(user);

        // Извлечение пользователя по ID
        User retrievedUser = userService.findById(user.getId()); // Используем автоматически сгенерированный ID
        Assertions.assertNotNull(retrievedUser);
        Assertions.assertEquals("testuser", retrievedUser.getUsername());
        Assertions.assertEquals("test@example.com", retrievedUser.getEmail());
        Assertions.assertNotNull(retrievedUser.getPassword());
    }
}

