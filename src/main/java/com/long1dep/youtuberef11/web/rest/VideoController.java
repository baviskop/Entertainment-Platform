package com.long1dep.youtuberef11.web.rest;

import com.long1dep.youtuberef11.service.dto.VideoDto;
import com.long1dep.youtuberef11.service.dto.request.VideoSearchRequest;
import com.long1dep.youtuberef11.service.dto.response.PagingResponse;
import com.long1dep.youtuberef11.service.dto.response.Response;
import jakarta.validation.Valid;

import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/video")
public interface VideoController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Response<VideoDto> create(@Valid @RequestBody final VideoDto dto);

    @PostMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    Response<PagingResponse<VideoDto>> getVideos(@RequestBody final VideoSearchRequest request);

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

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    Response<VideoDto> update(@RequestBody final VideoDto dto);

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    Response<Void> delete(@RequestBody final List<String> ids);
}
