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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
}
