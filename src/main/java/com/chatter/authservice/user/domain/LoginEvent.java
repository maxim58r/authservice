package com.chatter.authservice.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.modulith.events.ApplicationModuleListener;

@Slf4j
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginEvent {
    private String userId;
    private String username;
    private String timestamp;

    @ApplicationModuleListener
    void on(LoginEvent event) {
        log.info("User logged in: {}", event.getUserId());
    }
}

