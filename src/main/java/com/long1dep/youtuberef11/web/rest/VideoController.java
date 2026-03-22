package com.long1dep.youtuberef11.web.rest;

import com.long1dep.youtuberef11.service.dto.VideoDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/video")
public interface VideoController {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<VideoDto> create(@Valid @RequestBody final VideoDto dto);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Page<VideoDto>> getVideos();

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<VideoDto> getVideoById(@NotNull @PathVariable("id") final String id);

//    @PutMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    ResponseEntity<VideoDto> updateVideoById(@PathVariable("id") final String id, @Valid @RequestBody VideoDto dto);
//
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    ResponseEntity<Void> deleteById(@PathVariable("id") final String id);

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<VideoDto> update(@RequestBody final VideoDto dto);

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Void> delete(@RequestBody final List<String> ids);
}
