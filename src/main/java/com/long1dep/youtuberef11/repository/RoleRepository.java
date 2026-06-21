package com.long1dep.youtuberef11.repository;

import com.long1dep.youtuberef11.entity.RoleEntity;
import com.long1dep.youtuberef11.entity.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, String> {
    Optional<RoleEntity> findByName(ERole name);
}
