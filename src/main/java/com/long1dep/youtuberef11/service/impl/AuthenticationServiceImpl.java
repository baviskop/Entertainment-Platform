package com.long1dep.youtuberef11.service.impl;

import com.long1dep.youtuberef11.entity.AccountEntity;
import com.long1dep.youtuberef11.entity.RoleEntity;
import com.long1dep.youtuberef11.entity.enums.ERole;
import com.long1dep.youtuberef11.integration.minio.MinioChannel;
import com.long1dep.youtuberef11.repository.AccountRepository;
import com.long1dep.youtuberef11.repository.RoleRepository;
import com.long1dep.youtuberef11.security.jwt.TokenProvider;
import com.long1dep.youtuberef11.service.AuthenticationService;
import com.long1dep.youtuberef11.service.dto.AccountDto;
import com.long1dep.youtuberef11.service.dto.request.LoginRequest;
import com.long1dep.youtuberef11.service.dto.request.RegisterAccountRequest;
import com.long1dep.youtuberef11.service.dto.response.LoginResponse;
import com.long1dep.youtuberef11.service.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.http.HttpStatus;
import com.long1dep.youtuberef11.web.rest.error.BusinessException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    // Other
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    // Channel
    private final MinioChannel minioChannel;

    // Mapper
    private final AccountMapper accountMapper;

    // Repository
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;

    private final RedissonClient redissonClient;

    @Override
    public LoginResponse login(LoginRequest request) {
        final var authenticationToken = new UsernamePasswordAuthenticationToken(
                request.username(),
                request.password()
        );
        final var authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new LoginResponse(tokenProvider.createToken(authentication, request.rememberMe()));
    }

    @Override
    public AccountDto register(RegisterAccountRequest request) {
        final var account = new AccountEntity();
        RoleEntity useRole = roleRepository.findByName(ERole.USER)
                        .orElseThrow(() -> new RuntimeException("Lỗi: Không tìm thấy quyền USER trong hệ thống."));
        account.setUsername(request.getUsername());
        account.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        account.setUuid(UUID.randomUUID().toString());
        account.setRoles(List.of(useRole));
        account.setAvatar(minioChannel.upload(request.getAvatar()));
        return accountMapper.toDto(accountRepository.save(account));
    }

    @Override
    public void Logout(String token) {
        if (token != null && tokenProvider.validateToken(token)) {
            Date expirationDate = tokenProvider.getExpirationDateFromToken(token);

            long ttl = expirationDate.getTime() - System.currentTimeMillis();

            if (ttl > 0) {
                RBucket<String> bucket = redissonClient.getBucket("blacklist:token:"+ token);
                bucket.set("revoked", ttl, TimeUnit.MILLISECONDS);
            }
        }
    }

    @Override
    public AccountDto getCurrentUser() {
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof org.springframework.security.authentication.AnonymousAuthenticationToken) {
            throw new BusinessException(HttpStatus.UNAUTHORIZED.getReasonPhrase(), "Chưa đăng nhập");
        }
        String username = authentication.getName();
        return accountRepository.findByUsername(username)
                .map(accountMapper::toDto)
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND.getReasonPhrase(), "Không tìm thấy tài khoản: " + username));
    }
}
