package com.long1dep.youtuberef11.service.impl;

import com.long1dep.youtuberef11.config.properties.SecurityProperties;
import com.long1dep.youtuberef11.entity.AccountEntity;
import com.long1dep.youtuberef11.integration.minio.MinioChannel;
import com.long1dep.youtuberef11.repository.AccountRepository;
import com.long1dep.youtuberef11.repository.RoleRepository;
import com.long1dep.youtuberef11.security.jwt.TokenProvider;
import com.long1dep.youtuberef11.service.AuthService;
import com.long1dep.youtuberef11.service.dto.AccountDto;
import com.long1dep.youtuberef11.service.dto.request.AccountRegisterRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.long1dep.youtuberef11.service.dto.request.LoginRequest;
import com.long1dep.youtuberef11.service.dto.response.LoginResponse;
import com.long1dep.youtuberef11.service.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final SecurityProperties securityProperties;
//   Channel
    private final MinioChannel minioChannel;
//   Mapper
    private final AccountMapper accountMapper;

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
    public AccountDto register(AccountRegisterRequest request) {
        final var account = new AccountEntity();
        account.setUsername(request.getUsername());
        account.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        account.setUuid(UUID.randomUUID().toString());
        account.setRoles(roleRepository.findAll());
        account.setAvatar(minioChannel.upload(request.getAvatar()));
        return accountMapper.toDto(accountRepository.save(account));
    }
}
