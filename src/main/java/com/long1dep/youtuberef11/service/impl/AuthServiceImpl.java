package com.long1dep.youtuberef11.service.impl;

import com.long1dep.youtuberef11.common.utils.JwtUtil;
import com.long1dep.youtuberef11.config.properties.SecurityProperties;
import com.long1dep.youtuberef11.exception.AuthenticationException;
import com.long1dep.youtuberef11.repository.AccountRepository;
import com.long1dep.youtuberef11.service.AuthService;
import com.long1dep.youtuberef11.service.dto.request.LoginRequest;
import com.long1dep.youtuberef11.service.dto.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final SecurityProperties securityProperties;

    @Override
    public LoginResponse login(LoginRequest request) {
        final var account = accountRepository.findByUsername(request.username()).orElseThrow(() -> new AuthenticationException("Invalid username or password"));

        if(ObjectUtils.isEmpty(request.password()) || !passwordEncoder.matches(request.password(), account.getPasswordHash())) {
            throw new AuthenticationException("Invalid username or password");
        }

        final var token = JwtUtil.generateJwtToken(account, securityProperties.getJwtSecret(), securityProperties.getJwtExpiration());

        return new LoginResponse(token);
    }
}
