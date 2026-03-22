package com.long1dep.youtuberef11.web.rest.impl;

import com.long1dep.youtuberef11.service.VideoService;
import com.long1dep.youtuberef11.service.dto.VideoDto;
import com.long1dep.youtuberef11.web.rest.VideoController;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class VideoControllerImpl implements VideoController {

    private final VideoService videoService;

    @Override
    public ResponseEntity<VideoDto> create(@NonNull final VideoDto dto) {
        log.info("========= Create Video Request: {}", dto);
        final VideoDto video = videoService.create(dto);
        log.info("========= Create Video Response: {}", dto);

        return ResponseEntity
                .created(URI.create("/" + video.getId()))
                .body(video);
    }

    @Override
    public ResponseEntity<Page<VideoDto>> getVideos() {
        log.info("========= Get List Video =========");
        final Page<VideoDto> videos = videoService.getVideos();
        return ResponseEntity
                .ok()
                .body(videos);
    }

    @Override
    public ResponseEntity<VideoDto> getVideoById(@NonNull final String id) {
        log.info("========= Get Video Request: {}", id);
        final VideoDto video = videoService.getVideoById(id);
        log.info("========= Get Video Response: {}", video);
        return ResponseEntity
                .ok()
                .body(video);
    }

    @Override
    public ResponseEntity<VideoDto> update(@NonNull final VideoDto dto) {
        log.info("========= Update Video Request: {}", dto);
        final VideoDto video = videoService.update(dto);
        log.info("========= Update Video Response: {}", dto);

        return ResponseEntity
                .ok()
                .body(video);
    }

    @Override
    public ResponseEntity<Void> delete(List<String> ids) {
        log.info("========= Delete Video Request: {}", ids);
        videoService.delete(ids);
        return ResponseEntity
                .noContent()
                .build();
    }
}
