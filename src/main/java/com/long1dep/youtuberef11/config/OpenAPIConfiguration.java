package com.long1dep.youtuberef11.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class OpenAPIConfiguration implements WebMvcConfigurer {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("Course Application API")
                        .version("1.0")
                        .description("This is the API documentation for my Spring Boot application."));
    }
}
