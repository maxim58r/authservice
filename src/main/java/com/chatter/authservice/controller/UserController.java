package com.chatter.authservice.controller;

import com.chatter.authservice.service.UserService;
import com.chatter.authservice.entity.User;
import com.chatter.authservice.model.LoginRequest;
import com.chatter.authservice.model.LoginResponse;
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

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody User user) {
        log.info("Registering user {}", user);
        try {
            userService.registerUser(user);
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
            String token = userService.authenticateUser(loginRequest);
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
        userService.logout(username);
        log.info("Logout successful");
        return ResponseEntity.ok("Logged out successfully");
    }
}
