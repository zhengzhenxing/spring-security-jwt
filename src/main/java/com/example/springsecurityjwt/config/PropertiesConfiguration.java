package com.example.springsecurityjwt.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertiesConfiguration {
    @Bean
    @ConfigurationProperties(prefix = "token")
    public TokenProperties tokenProperties() {
        return new TokenProperties();
    }
}
