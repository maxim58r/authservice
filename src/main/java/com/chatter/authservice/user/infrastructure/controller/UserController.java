package com.chatter.authservice.user.infrastructure.controller;

import com.chatter.authservice.user.application.LoginUser;
import com.chatter.authservice.user.domain.User;
import com.chatter.authservice.user.api.LoginRequest;
import com.chatter.authservice.user.api.LoginResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class UserController {

    private final LoginUser loginUser;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody User user) {
        log.info("Registering user {}", user);
        try {
            loginUser.registerUser(user);
            log.info("User registered successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        log.info("Login request {}", loginRequest);
        try {
            String token = loginUser.authenticateUser(loginRequest);
            log.info("Login successful");
            return ResponseEntity.ok(new LoginResponse(token));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestParam String username) {
        log.info("Logout request {}", username);
        loginUser.logout(username);
        log.info("Logout successful");
        return ResponseEntity.ok("Logged out successfully");
    }
}
