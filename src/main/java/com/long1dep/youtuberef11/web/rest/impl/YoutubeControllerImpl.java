package com.long1dep.youtuberef11.web.rest.impl;

import com.long1dep.youtuberef11.integration.youtube.YoutubeChannel;
import com.long1dep.youtuberef11.integration.youtube.model.YoutubeItem;
import com.long1dep.youtuberef11.service.dto.response.Response;
import com.long1dep.youtuberef11.web.rest.YoutubeController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class YoutubeControllerImpl implements YoutubeController {
    private final YoutubeChannel youtubeChannel;

    @Override
    public Response<List<YoutubeItem>> search(final String search, final int maxSize) {
        return Response.ok(youtubeChannel.search(search, maxSize));
    }
}
