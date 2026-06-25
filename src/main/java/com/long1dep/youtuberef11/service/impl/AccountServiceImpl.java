package com.long1dep.youtuberef11.service.impl;

import com.long1dep.youtuberef11.entity.AccountEntity;
import com.long1dep.youtuberef11.repository.AccountRepository;
import com.long1dep.youtuberef11.service.AccountService;
import com.long1dep.youtuberef11.service.dto.AccountAdminDto;
import com.long1dep.youtuberef11.web.rest.error.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<AccountAdminDto> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(this::toAdminDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void resetPassword(String accountId, String newPassword) {
        AccountEntity account = accountRepository.findById(accountId)
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND.getReasonPhrase(), "Không tìm thấy tài khoản"));
        account.setPasswordHash(passwordEncoder.encode(newPassword));
        accountRepository.save(account);
    }

    private AccountAdminDto toAdminDto(AccountEntity entity) {
        AccountAdminDto dto = new AccountAdminDto();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setAvatar(entity.getAvatar());
        if (entity.getRoles() != null) {
            dto.setRoles(entity.getRoles().stream()
                    .map(role -> role.getName().name())
                    .collect(Collectors.toList()));
        }
        return dto;
    }
}
