package com.long1dep.youtuberef11.service;

import com.long1dep.youtuberef11.service.dto.VideoDto;
import lombok.NonNull;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VideoService {

    VideoDto getVideoById(@NonNull final String id);

    Page<VideoDto> getVideos();

    VideoDto create(@NonNull final VideoDto dto);

    VideoDto update(@NonNull final VideoDto dto);

    void delete(@NonNull final List<String> ids);
}
