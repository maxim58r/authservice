package com.chatter.authservice.config;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.testcontainers.containers.GenericContainer;
import redis.embedded.RedisServer;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RedisConfigTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static RedisServer redisServer;
    private static GenericContainer<?> redisContainer;

    @BeforeAll
    static void startRedis() throws Exception {
        boolean embeddedEnabled = Boolean.parseBoolean(System.getProperty("embedded.redis.enabled", "true"));

        if (embeddedEnabled) {
            // Embedded Redis
            redisServer = new RedisServer(6379);
            redisServer.start();
        } else {
            // Testcontainers Redis
            redisContainer = new GenericContainer<>("redis:6.2.6").withExposedPorts(6379);
            redisContainer.start();
            System.setProperty("spring.redis.host", redisContainer.getHost());
            System.setProperty("spring.redis.port", redisContainer.getMappedPort(6379).toString());
        }
    }

    @AfterAll
    static void stopRedis() throws IOException {
        if (redisServer != null) {
            redisServer.stop();
        }
        if (redisContainer != null) {
            redisContainer.stop();
        }
    }

    @Test
    void testRedisConnection() {
        String key = "testKey";
        String value = "testValue";

        redisTemplate.opsForValue().set(key, value);
        String result = (String) redisTemplate.opsForValue().get(key);

        assertEquals(value, result);
    }

    @Configuration
    static class RedisTestConfig {

        @Bean
        public LettuceConnectionFactory redisConnectionFactory() {
            String host = System.getProperty("spring.redis.host", "localhost");
            int port = Integer.parseInt(System.getProperty("spring.redis.port", "6379"));
            return new LettuceConnectionFactory(host, port);
        }

        @Bean
        public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory connectionFactory) {
            RedisTemplate<String, Object> template = new RedisTemplate<>();
            template.setConnectionFactory(connectionFactory);
            return template;
        }
    }
}
