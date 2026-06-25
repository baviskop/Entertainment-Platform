package com.long1dep.youtuberef11.event.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component
@RequiredArgsConstructor
public class VideoConsumer {
    private final SimpMessageSendingOperations messagingTemplate;

    @Bean
    public Consumer<String> receiveVideos() {
        return content -> {
            log.info("Kafka Video Consumer received: {}", content);

            messagingTemplate.convertAndSend("/notification/new-video", content);

            log.info("Successfully pushed notification to WebSocket channel: /notification/new-video");
        };
    }
}
