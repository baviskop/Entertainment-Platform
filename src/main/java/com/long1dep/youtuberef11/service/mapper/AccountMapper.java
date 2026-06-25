package com.long1dep.youtuberef11.service.mapper;

import com.long1dep.youtuberef11.entity.AccountEntity;
import com.long1dep.youtuberef11.entity.RoleEntity;
import com.long1dep.youtuberef11.service.dto.AccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(config = DefaultConfigMapper.class)
public interface AccountMapper extends EntityMapper<AccountDto, AccountEntity> {

    @Override
    @Mapping(target = "roles", source = "roles", qualifiedByName = "mapRoles")
    AccountDto toDto(AccountEntity entity);

    @Override
    @Mapping(target = "roles", ignore = true)
    AccountEntity toEntity(AccountDto dto);

    @Override
    @Mapping(target = "roles", ignore = true)
    void update(AccountDto dto, @MappingTarget AccountEntity entity);

    @Override
    @Mapping(target = "roles", ignore = true)
    void updateDto(@MappingTarget AccountDto dto, AccountEntity entity);

    @Named("mapRoles")
    default List<String> mapRoles(List<RoleEntity> roles) {
        if (roles == null) {
            return Collections.emptyList();
        }
        return roles.stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toList());
    }
}
