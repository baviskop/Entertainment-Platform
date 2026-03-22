package com.long1dep.youtuberef11.web.rest.impl;

import com.long1dep.youtuberef11.service.dto.VideoDto;
import com.long1dep.youtuberef11.web.rest.VideoController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class VideoControllerImpl implements VideoController {
    @Override
    public ResponseEntity<VideoDto> create(VideoDto dto) {
        return null;
    }

    @Override
    public ResponseEntity<Page<VideoDto>> getVideos() {
        return null;
    }

    @Override
    public ResponseEntity<VideoDto> getVideoById(String id) {
        return null;
    }

    @Override
    public ResponseEntity<VideoDto> update(VideoDto dto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> delete(List<String> ids) {
        return null;
    }
}
