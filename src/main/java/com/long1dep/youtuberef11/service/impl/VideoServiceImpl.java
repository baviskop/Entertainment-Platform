package com.long1dep.youtuberef11.service.impl;

import com.long1dep.youtuberef11.entity.VideoEntity;
import com.long1dep.youtuberef11.entity.enums.VideoStatus;
import com.long1dep.youtuberef11.integration.minio.MinioChannel;
import com.long1dep.youtuberef11.repository.VideoRepository;
import com.long1dep.youtuberef11.security.SecurityUtils;
import com.long1dep.youtuberef11.service.VideoService;
import com.long1dep.youtuberef11.service.dto.VideoDto;
import com.long1dep.youtuberef11.service.dto.request.CreateVideoRequest;
import com.long1dep.youtuberef11.service.dto.request.VideoSearchRequest;
import com.long1dep.youtuberef11.service.mapper.VideoMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;
    private final VideoMapper videoMapper;
    private final MinioChannel minioChannel;

    private final RedissonClient redissonClient;
    private final HttpServletRequest request;

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
            final VideoEntity oldEntity = videoRepository.findById(id).orElseThrow();
            final String oldThumbnail = oldEntity.getThumbnail();

            final VideoEntity entity = videoMapper.toEntity(dto);
            final VideoDto updatedVideo = videoMapper.toDto(videoRepository.save(entity));

            if (oldThumbnail != null && !oldThumbnail.equals(dto.getThumbnail())) {
                minioChannel.deleteFile(oldThumbnail);
            }
            return updatedVideo;
        }
        throw new RuntimeException("Không tìm thấy video với id là: " + id);
    }

    @Override
    public void delete(@NonNull final List<String> ids) {
        final List<VideoEntity> videos = videoRepository.findAllByIdIn(ids);
        videos.forEach(video -> video.setStatus(VideoStatus.DELETED));
        videoRepository.saveAll(videos);
    }

    @Override
    public void increaseViews(@NonNull final String id) {
        if(!videoRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy video với id là: " + id);
        }

        final String viewerIdentifier = SecurityUtils.getCurrentUserLogin().orElseGet(this::getClientIp);
        final String lockKey = "view_lock:" + id + ":" + viewerIdentifier;

        RBucket<String> lockBucket = redissonClient.getBucket(lockKey);

        if (lockBucket.isExists()) {
            log.info("Spam view detected for video: {} by: {}. Request ignored.", id, viewerIdentifier);
            return;
        }
        lockBucket.set("locked", 10, TimeUnit.MINUTES);
        videoRepository.increaseViewsById(id);
        log.info("Successfully increased view count for video: {} by user/ip: {}", id, viewerIdentifier);
    }

    //helper
    private String getClientIp() {
        String ipaddress = request.getHeader("X-Forwarded-For");
        if (ipaddress == null || ipaddress.isEmpty() || "unknown".equalsIgnoreCase(ipaddress)) {
            ipaddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipaddress == null || ipaddress.isEmpty() || "unknown".equalsIgnoreCase(ipaddress)) {
            ipaddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipaddress == null || ipaddress.isEmpty() || "unknown".equalsIgnoreCase(ipaddress)) {
            ipaddress = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipaddress == null || ipaddress.isEmpty() || "unknown".equalsIgnoreCase(ipaddress)) {
            ipaddress = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ipaddress == null || ipaddress.isEmpty() || "unknown".equalsIgnoreCase(ipaddress)) {
            ipaddress = request.getRemoteAddr();
        }
        return ipaddress;
    }
}
