package com.chatter.authservice.service;

import com.chatter.authservice.model.LoginRequest;
import com.chatter.authservice.model.User;

public interface UserService {
    void registerUser(User user);

    String authenticateUser(LoginRequest loginRequest);
}
