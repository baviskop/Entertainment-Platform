package com.long1dep.youtuberef11.service.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreateVideoRequest {
    private String id;
    private String url;
    private String description;
    private MultipartFile thumbnail;
    private com.long1dep.youtuberef11.entity.enums.VideoStatus status;
}
