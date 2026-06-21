package com.long1dep.youtuberef11.security.jwt;

import com.long1dep.youtuberef11.config.filter.AuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JWTConfigurer implements SecurityConfigurer<DefaultSecurityFilterChain, HttpSecurity> {
    private final AuthenticationFilter authenticationFilter;

    @Override
    public void init(final HttpSecurity http) throws Exception {
    }

    @Override
    public void configure(HttpSecurity http) {
        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
