package com.long1dep.youtuberef11.config;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfiguration {
    @Bean
    public JavaTimeModule javaTimeModule() {
        return new JavaTimeModule();
    }

}
