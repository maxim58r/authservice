package com.chatter.authservice.user.api;

import lombok.*;

@Builder
public record LoginResponse(String token) {
}
