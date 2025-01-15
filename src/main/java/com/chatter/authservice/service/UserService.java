package com.chatter.authservice.service;

import com.chatter.authservice.model.LoginRequest;
import com.chatter.authservice.entity.User;

public interface UserService {
    void registerUser(User user);

    String authenticateUser(LoginRequest loginRequest);

    void logout(String username);

    User findById(long id);
}
