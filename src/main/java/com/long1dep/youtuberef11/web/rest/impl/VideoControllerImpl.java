package com.long1dep.youtuberef11.web.rest.impl;

import com.long1dep.youtuberef11.service.VideoService;
import com.long1dep.youtuberef11.service.dto.VideoDto;
import com.long1dep.youtuberef11.service.dto.request.PagingRequest;
import com.long1dep.youtuberef11.service.dto.request.VideoSearchRequest;
import com.long1dep.youtuberef11.service.dto.response.PageableData;
import com.long1dep.youtuberef11.service.dto.response.PagingResponse;
import com.long1dep.youtuberef11.service.dto.response.Response;
import com.long1dep.youtuberef11.web.rest.VideoController;
import com.long1dep.youtuberef11.web.rest.error.BusinessException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;

import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
public class VideoControllerImpl implements VideoController {

    private final VideoService videoService;

    @Override
    public Response<VideoDto> create(@NonNull final VideoDto dto) {
        if (!ObjectUtils.isEmpty(dto.getId())) {
            throw new BusinessException(HttpStatus.BAD_REQUEST.getReasonPhrase(), "Id không được để trống");
        }
        log.info("========= Create Video Request: {}", dto);
        final VideoDto video = videoService.create(dto);
        log.info("========= Create Video Response: {}", dto);

        return Response.created(video);
    }

    @Override
    public Response<PagingResponse<VideoDto>> getVideos(@RequestBody final VideoSearchRequest request) {
        log.info("========= Get List Video =========");
        final Page<VideoDto> videos = videoService.getVideos(request);
        final PagingRequest paging = request.getPaging();
        return Response
                .ok(
                        new PagingResponse<VideoDto>()
                                .setContents(videos.getContent())
                                .setPaging(
                                        new PageableData()
                                                .setPageNumber(paging.getPage() - 1)
                                                .setPageSize(paging.getSize())
                                                .setTotalPages(videos.getTotalPages())
                                                .setTotalRecord(videos.getTotalElements())
                                )
                );
    }

    @Override
    public Response<VideoDto> getVideoById(@NonNull final String id) {
        log.info("========= Get Video Request: {}", id);
        final VideoDto video = videoService.getVideoById(id);
        log.info("========= Get Video Response: {}", video);
        return Response
                .ok(video);

    }

    @Override
    public Response<VideoDto> update(@NonNull final VideoDto dto) {
        log.info("========= Update Video Request: {}", dto);
        final VideoDto video = videoService.update(dto);
        log.info("========= Update Video Response: {}", dto);

        return Response
                .ok(video);

    }

    @Override
    public Response<Void> delete(List<String> ids) {
        log.info("========= Delete Video Request: {}", ids);
        videoService.delete(ids);
        return Response
                .noContent();

    }
}
