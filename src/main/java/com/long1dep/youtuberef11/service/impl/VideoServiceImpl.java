package com.long1dep.youtuberef11.service.impl;

import com.long1dep.youtuberef11.entity.VideoEntity;
import com.long1dep.youtuberef11.entity.enums.VideoStatus;
import com.long1dep.youtuberef11.integration.minio.MinioChannel;
import com.long1dep.youtuberef11.repository.VideoRepository;
import com.long1dep.youtuberef11.service.VideoService;
import com.long1dep.youtuberef11.service.dto.VideoDto;
import com.long1dep.youtuberef11.service.dto.request.CreateVideoRequest;
import com.long1dep.youtuberef11.service.dto.request.VideoSearchRequest;
import com.long1dep.youtuberef11.service.mapper.VideoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;
    private final VideoMapper videoMapper;
    private final MinioChannel minioChannel;

    @Override
    public VideoDto getVideo(@NonNull final String id) {
        return videoRepository.findById(id)
                .map(videoMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Video không tồn tại với id là: " + id));
    }

    @Override
    public Page<VideoDto> getVideos(@NonNull final VideoSearchRequest request) {
        return videoRepository.findAll(request.specification(), request.getPaging().pageable())
                .map(videoMapper::toDto);
    }

    @Override
    public VideoDto create(@NonNull final CreateVideoRequest request) {
        final VideoEntity entity = new VideoEntity();
        entity.setId(request.getId());
        entity.setUrl(request.getUrl());
        entity.setDescription(request.getDescription());
        entity.setViews(0L);
        entity.setStatus(VideoStatus.ACTIVE);

        if (request.getThumbnail() != null && !request.getThumbnail().isEmpty()) {
            String thumbnailUrl = minioChannel.upload(request.getThumbnail());
            entity.setThumbnail(thumbnailUrl);
        }
        return videoMapper.toDto(videoRepository.save(entity));
    }

    @Override
    public VideoDto update(@NonNull final VideoDto dto) {
        final String id = dto.getId();
        if (videoRepository.existsById(id)) {
            final VideoEntity entity = videoMapper.toEntity(dto);
            return videoMapper.toDto(videoRepository.save(entity));
        }
        throw new RuntimeException("Không tìm thấy video với id là :" + id);
    }

    @Override
    public void delete(@NonNull final List<String> ids) {
        final List<VideoEntity> videos = videoRepository.findAllByIdIn(ids);
        videos.forEach(video -> video.setStatus(VideoStatus.DELETED));
        videoRepository.saveAll(videos);
    }
}
