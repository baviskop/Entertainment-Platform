package com.long1dep.youtuberef11.service;

import com.long1dep.youtuberef11.service.dto.VideoDto;
import com.long1dep.youtuberef11.service.dto.request.CreateVideoRequest;
import com.long1dep.youtuberef11.service.dto.request.VideoSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;

import java.util.List;

public interface VideoService {
    VideoDto getVideo(@NonNull final String id);

    Page<VideoDto> getVideos(final VideoSearchRequest request);

    VideoDto create(@NonNull final CreateVideoRequest request);

    VideoDto update(@NonNull final VideoDto dto);

    void delete(@NonNull final List<String> ids);

}
