package com.chatter.authservice.user.application;

import com.chatter.authservice.user.api.LoginRequest;
import com.chatter.authservice.user.domain.User;

public interface LoginUser {
    void registerUser(User user);

    String authenticateUser(LoginRequest loginRequest);

    void logout(String username);

    User findById(long id);
}
