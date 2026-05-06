package com.long1dep.youtuberef11.config.init;

import com.long1dep.youtuberef11.entity.AccountEntity;
import com.long1dep.youtuberef11.entity.RoleEntity;
import com.long1dep.youtuberef11.entity.enums.ERole;
import com.long1dep.youtuberef11.repository.AccountRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountInit implements CommandLineRunner {
    AccountRepository accountRepo;
    PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {
        if (accountRepo.count() > 0) {
            return;
        }

        final var account =AccountEntity.builder()
                .username("hlong030202k6@gmail.com")
                .passwordHash(this.passwordEncoder.encode("long0302"))
                .uuid(UUID.randomUUID().toString())
                .roles(List.of(
                        RoleEntity.builder()
                                .name(ERole.ADMIN)
                                .build(),
                        RoleEntity.builder()
                                .name(ERole.USER)
                                .build()
                ))
                .build();
        this.accountRepo.save(account);
    }
}
