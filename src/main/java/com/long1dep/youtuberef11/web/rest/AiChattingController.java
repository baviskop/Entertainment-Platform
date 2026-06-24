package com.long1dep.youtuberef11.web.rest;

import com.long1dep.youtuberef11.service.AiChattingService;
import com.long1dep.youtuberef11.service.dto.AiChatDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AiChattingController {
    private final AiChattingService aiChattingService;

    @PostMapping("/ai-message")
    String chatAI(@RequestBody AiChatDto request) {
        return aiChattingService.generation(request);
    }
}
