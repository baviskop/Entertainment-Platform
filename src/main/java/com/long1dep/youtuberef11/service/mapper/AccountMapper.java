package com.long1dep.youtuberef11.service.mapper;

import com.long1dep.youtuberef11.entity.AccountEntity;
import com.long1dep.youtuberef11.service.dto.AccountDto;
import org.mapstruct.Mapper;

@Mapper(config = DefaultConfigMapper.class)
public interface AccountMapper extends EntityMapper<AccountDto, AccountEntity> {

}
