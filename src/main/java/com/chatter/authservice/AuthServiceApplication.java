package com.chatter.authservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/**
 * Main class for the authentication service.
 */
@Slf4j
@SpringBootApplication
@ConfigurationPropertiesScan
public class AuthServiceApplication {
    /**
     * Entry point for the authentication service application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        log.debug("Environment Variables:");
        log.debug("IFT_REDIS_HOST: {}", System.getenv("IFT_REDIS_HOST"));
        log.debug("IFT_REDIS_PORT: {}", System.getenv("IFT_REDIS_PORT"));
        log.debug("IFT_REDIS_PASSWORD: {}", System.getenv("IFT_REDIS_PASSWORD"));

        SpringApplication.run(AuthServiceApplication.class, args);
    }

}
