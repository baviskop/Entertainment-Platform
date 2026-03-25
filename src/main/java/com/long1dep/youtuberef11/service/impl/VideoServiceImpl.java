package com.long1dep.youtuberef11.service.impl;

import com.long1dep.youtuberef11.entity.VideoEntity;
import com.long1dep.youtuberef11.entity.enums.VideoStatus;
import com.long1dep.youtuberef11.repository.VideoRepository;
import com.long1dep.youtuberef11.service.VideoService;
import com.long1dep.youtuberef11.service.dto.VideoDto;
import com.long1dep.youtuberef11.service.dto.request.VideoSearchRequest;
import com.long1dep.youtuberef11.service.mapper.VideoMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {
    private final VideoRepository videoRepo;
    private final VideoMapper videoMapper;
    @Override
    public VideoDto getVideoById(@NonNull final String id) {

        return videoRepo.findById(id)
                .map(videoMapper::toDto) // (entity -> VideoDto.from(entity)) Gọi là method references
                .orElseThrow(() -> new RuntimeException("Video không tồn tại với id là: " + id));
    }

    @Override
    public Page<VideoDto> getVideos(@NonNull final VideoSearchRequest request) {
        return videoRepo.findAll(request.specification(), request.getPaging().pageable()).map(videoMapper::toDto);
    }

    @Override
    public VideoDto create(@NonNull final VideoDto dto) {
        final VideoEntity entity = videoMapper.toEntity(dto);
        return videoMapper.toDto(videoRepo.save(entity));
    }

    @Override
    public VideoDto update(@NonNull final VideoDto dto) {
        final String id = dto.getId();
        if (videoRepo.existsById(id)) {
            final VideoEntity entity = videoMapper.toEntity(dto);
            return videoMapper.toDto(videoRepo.save(entity));
        }
        throw new RuntimeException("Không tìm thấy video với id là: " + id);
    }

    @Override
    public void delete(@NonNull final List<String> ids) {
        final List<VideoEntity> videos = videoRepo.findAllByIdIn(ids);
        videos.forEach(video -> video.setStatus(VideoStatus.DELETED));
        videoRepo.saveAll(videos);
    }
}
