package com.long1dep.youtuberef11.service.mapper;

import com.long1dep.youtuberef11.entity.VideoEntity;
import com.long1dep.youtuberef11.service.dto.VideoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(
        config = DefaultConfigMapper.class,
        builder = @org.mapstruct.Builder(disableBuilder = true)
)
public interface VideoMapper extends EntityMapper<VideoDto, VideoEntity> {
    @Override
    @Mapping(source = "lastModifiedDate", target = "updatedAt")
    VideoDto toDto(VideoEntity entity);

    @Override
    @Mapping(source = "updatedAt", target = "lastModifiedDate")
    VideoEntity toEntity(VideoDto dto);

    @Override
    @Mapping(source = "updatedAt", target = "lastModifiedDate")
    void update(VideoDto dto, @MappingTarget VideoEntity entity);

    @Override
    @Mapping(source = "lastModifiedDate", target = "updatedAt")
    void updateDto(@MappingTarget VideoDto dto, VideoEntity entity);
}

