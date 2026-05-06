package com.long1dep.youtuberef11.security;

import com.long1dep.youtuberef11.entity.AccountEntity;
import com.long1dep.youtuberef11.entity.RoleEntity;
import com.long1dep.youtuberef11.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
@Component("userDetailsService")
@RequiredArgsConstructor
public class DomainUserDetailsService implements UserDetailsService {
    private final AccountRepository accountRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        log.debug("Authenticating {}", username);
        if (new EmailValidator().isValid(username, null)) {
            return accountRepository
                    .findByUsername(username)
                    .map(account -> createSpringSecurityUser(username, account))
                    .orElseThrow(() -> new UsernameNotFoundException("User with email " + username + " was not found in the database"));
        }
        String lowercaseLogin = username.toLowerCase(Locale.ENGLISH);
        return accountRepository
                .findByUsername(lowercaseLogin)
                .map(account -> createSpringSecurityUser(lowercaseLogin, account))
                .orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database"));
    }

    private User createSpringSecurityUser(String lowercaseLogin, AccountEntity account) {

        List<GrantedAuthority> grantedAuthorities = account
                .getRoles()
                .stream()
                .map(RoleEntity::getName)
                .map(Enum::name)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new User(account.getUsername(), account.getPasswordHash(), grantedAuthorities);
    }
}
