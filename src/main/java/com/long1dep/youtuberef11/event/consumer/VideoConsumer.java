package com.long1dep.youtuberef11.event.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component
public class VideoConsumer {

    @Bean
    public Consumer<String> receiveVideos() {
        return content -> log.info("Video consumer: {}", content);
    }
}
