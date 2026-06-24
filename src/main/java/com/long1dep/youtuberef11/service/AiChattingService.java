package com.long1dep.youtuberef11.service;

import com.long1dep.youtuberef11.service.dto.AiChatDto;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiChattingService {
    private final ChatClient chatClient;

    public AiChattingService(ChatClient.Builder chatClient) {
        this.chatClient = chatClient
                .defaultSystem("Tôi là Bavis, trợ lý ảo của Long senpai, Tôi có thể giúp gì cho bạn ?")
                .build();
    }

    public String generation(AiChatDto request) {
        return chatClient.prompt()
                .user(request.getQuestion())
                .call()
                .content();
    }
}
