package com.long1dep.youtuberef11.repository;

import com.long1dep.youtuberef11.entity.AccountEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity,String> {
    @EntityGraph(attributePaths = "roles")
    Optional<AccountEntity> findByUuid(String uuid);
//    String ACCOUNT_BY_EMAIL_CACHE = "accountByEmail";
//
//    @Cacheable(ACCOUNT_BY_EMAIL_CACHE)
    Optional<AccountEntity> findByUsername(String username);
}
