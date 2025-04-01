package com.chatter.authservice.user.api;

import lombok.*;

@Builder
public record LoginRequest(String username, CharSequence password) {
}
