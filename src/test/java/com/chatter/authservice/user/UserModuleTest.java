package com.chatter.authservice.user;

import com.chatter.authservice.user.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.test.context.ActiveProfiles;

@ApplicationModuleTest
@ActiveProfiles("test")
class UserModuleTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void loginWorks() {
        // можно замокать данные и вызвать useCase.login()
    }
}

