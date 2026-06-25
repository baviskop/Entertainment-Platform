package com.long1dep.youtuberef11.web.rest.impl;

import com.long1dep.youtuberef11.service.VideoService;
import com.long1dep.youtuberef11.service.dto.DashboardStatsDto;
import com.long1dep.youtuberef11.service.dto.VideoDto;
import com.long1dep.youtuberef11.service.dto.request.CreateVideoRequest;
import com.long1dep.youtuberef11.service.dto.request.PagingRequest;
import com.long1dep.youtuberef11.service.dto.request.UpdateVideoRequest;
import com.long1dep.youtuberef11.service.dto.request.VideoSearchRequest;
import com.long1dep.youtuberef11.service.dto.response.PageableData;
import com.long1dep.youtuberef11.service.dto.response.PagingResponse;
import com.long1dep.youtuberef11.service.dto.response.Response;
import com.long1dep.youtuberef11.web.rest.VideoController;
import com.long1dep.youtuberef11.web.rest.error.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class VideoControllerImpl implements VideoController {

    private final VideoService videoService;

    @Override
    public Response<VideoDto> create(@ModelAttribute @NonNull final CreateVideoRequest request) {
        if (!ObjectUtils.isEmpty(request.getId())) {
            throw new BusinessException(HttpStatus.BAD_REQUEST.getReasonPhrase(), "Id cannot blank when creating video");
        }
        log.info("======== Create video request: {}", request.getUrl());
        final VideoDto video = videoService.create(request);
        log.info("======== Create video response: {}", video);

        return Response
                .created(video);
    }

    @Override
    public Response<PagingResponse<VideoDto>> getVideos(@RequestBody final VideoSearchRequest request) {
        log.info("======== Get list video ========");
        final Page<VideoDto> videos = videoService.getVideos(request);
        final PagingRequest paging = request.getPaging();
        return Response
                .ok(
                        new PagingResponse<VideoDto>()
                                .setContents(videos.getContent())
                                .setPaging(
                                        new PageableData()
                                                .setPageNumber(Math.max(paging.getPage() - 1, 0))
                                                .setTotalPage(videos.getTotalPages())
                                                .setPageSize(paging.getSize())
                                                .setTotalRecord(videos.getTotalElements())
                                )
                );
    }

    @Override
    public Response<VideoDto> getVideo(@NonNull final String id) {
        log.info("======== Get video request: {}", id);
        final VideoDto video = videoService.getVideo(id);
        log.info("======== Get video response: {}", video);
        return Response
                .ok(video);
    }

    @Override
    public Response<VideoDto> update(@NonNull @ModelAttribute final UpdateVideoRequest request) {
        if (ObjectUtils.isEmpty(request.getId())) {
            throw new BusinessException(HttpStatus.BAD_REQUEST.getReasonPhrase(), "Id cannot blank when updating video");
        }
        log.info("======== Update video request: {}", request.getUrl());
        final VideoDto video = videoService.update(request);
        log.info("======== Update video response: {}", video);
        return Response.ok(video);
    }

    @Override
    public Response<Void> delete(@NonNull final List<String> ids) {
        log.info("======== Delete video request: {}", ids);
        videoService.delete(ids);
        return Response
                .noContent();
    }

    @Override
    public Response<Void> increaseViews(@NonNull @PathVariable("id") final String id) {
        log.info("======== Increase video views request: {}", id);
        videoService.increaseViews(id);
        log.info("======== Increase video views response: {}", id);
        return Response.ok(null);
    }

    @Override
    public Response<DashboardStatsDto> getStats() {
        log.info("======== Get dashboard stats request ========");
        final DashboardStatsDto stats = videoService.getDashboardStats();
        log.info("======== Get dashboard stats response: {}", stats);
        return Response.ok(stats);
    }
}
