package com.long1dep.youtuberef11.service.dto;

import com.long1dep.youtuberef11.entity.VideoEntity;
import com.long1dep.youtuberef11.entity.enums.VideoStatus;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoDto {
    private String id;
    private String url;
    private String description;
    @Builder.Default
    private VideoStatus status = VideoStatus.DRAFT;

    public static VideoDto from(@NonNull final VideoEntity entity) {
        return VideoDto.builder()
                .id(entity.getId())
                .url(entity.getUrl())
                .description(entity.getDescription())
                .status(entity.getStatus())
                .build();
    }

    public VideoEntity toEntity() {
        return VideoEntity.builder()
                .id(this.getId())
                .url(this.getUrl())
                .description((this.getDescription()))
                .status(this.getStatus())
                .build();
    }
}
