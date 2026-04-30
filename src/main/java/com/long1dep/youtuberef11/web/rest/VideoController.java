package com.long1dep.youtuberef11.web.rest;

import com.long1dep.youtuberef11.service.dto.VideoDto;
import com.long1dep.youtuberef11.service.dto.request.VideoSearchRequest;
import com.long1dep.youtuberef11.service.dto.response.PagingResponse;
import com.long1dep.youtuberef11.service.dto.response.Response;
import jakarta.validation.Valid;

import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/_api/v1/admin/video")
public interface VideoController {
    @Secured("ROLE_ADMIN")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Response<VideoDto> create(@Valid @RequestBody final VideoDto dto);

    @Secured("ROLE_ADMIN")
    @PostMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    Response<PagingResponse<VideoDto>> getVideos(@RequestBody final VideoSearchRequest request);

    @Secured("ROLE_ADMIN")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Response<VideoDto> getVideoById(@NonNull @PathVariable("id") final String id);

//    @PutMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    ResponseEntity<VideoDto> updateVideoById(@PathVariable("id") final String id, @Valid @RequestBody VideoDto dto);
//
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    ResponseEntity<Void> deleteById(@PathVariable("id") final String id);

    @Secured("ROLE_ADMIN")
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    Response<VideoDto> update(@RequestBody final VideoDto dto);

    @Secured("ROLE_ADMIN")
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    Response<Void> delete(@RequestBody final List<String> ids);
}
