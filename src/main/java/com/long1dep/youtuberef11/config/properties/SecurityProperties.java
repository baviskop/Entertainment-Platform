package com.long1dep.youtuberef11.config.properties;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(
        prefix = "jwt",
        ignoreUnknownFields = false
)
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SecurityProperties {
    String jwtSecret;
    int jwtExpiration;
}
