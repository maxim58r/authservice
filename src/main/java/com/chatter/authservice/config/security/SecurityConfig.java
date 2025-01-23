package com.chatter.authservice.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/actuator/health", "/actuator/prometheus").permitAll() // Разрешить доступ к /actuator/health без авторизации
                        .anyExchange().authenticated()                // Остальные запросы требуют авторизации
                )
                .csrf(ServerHttpSecurity.CsrfSpec::disable)        // Если нужно отключить CSRF
                .httpBasic(Customizer.withDefaults())              // Включение HTTP Basic авторизации
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                );

        return http.build();
    }
}

