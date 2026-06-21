package com.long1dep.youtuberef11.service.mapper;

import com.long1dep.youtuberef11.entity.VideoEntity;
import com.long1dep.youtuberef11.service.dto.VideoDto;
import org.mapstruct.Mapper;

@Mapper(
        config = DefaultConfigMapper.class
)
public interface VideoMapper extends EntityMapper<VideoDto, VideoEntity> {
}
