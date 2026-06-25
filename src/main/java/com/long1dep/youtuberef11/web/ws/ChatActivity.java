package com.long1dep.youtuberef11.web.ws;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@MessageMapping("/chat")
public class ChatActivity {

    private final SimpMessagingTemplate messagingTemplate;
    private final ObjectMapper objectMapper;

    @MessageMapping("/lobby/{lobbyId}/message")
    @SendTo("/chat/lobby/{lobbyId}/message/data")
    public String joinLobby(
            @DestinationVariable String lobbyId,
            @Payload String message
    ) {
        try {
            // Check sender role. If it is not admin, notify admins via /notification/support
            Map<String, Object> msgMap = objectMapper.readValue(message, Map.class);
            String sender = (String) msgMap.get("sender");
            String content = (String) msgMap.get("content");
            if (sender != null && !sender.equalsIgnoreCase("admin")) {
                messagingTemplate.convertAndSend("/notification/support", message);
            }
        } catch (Exception e) {
            log.warn("Error processing chat message for admin notification: {}", e.getMessage());
        }
        return message;
    }
}
