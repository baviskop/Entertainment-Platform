package com.long1dep.youtuberef11.service.impl;

import com.long1dep.youtuberef11.service.VideoService;
import com.long1dep.youtuberef11.service.dto.VideoDto;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class VideoServiceImpl implements VideoService {
    @Override
    public VideoDto getVideoById(@NonNull String id) {
        return null;
    }

    @Override
    public Page<VideoDto> getVideos() {
        return null;
    }

    @Override
    public VideoDto create(@NonNull VideoDto dto) {
        return null;
    }

    @Override
    public VideoDto update(@NonNull VideoDto dto) {
        return null;
    }

    @Override
    public void delete(@NonNull List<String> ids) {

    }
}
