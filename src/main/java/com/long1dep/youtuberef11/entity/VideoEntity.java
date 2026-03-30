package com.long1dep.youtuberef11.entity;

import com.long1dep.youtuberef11.entity.enums.VideoStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "video")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoEntity extends AbstractAuditingEntity<String> {
    @Id
    @UuidGenerator
    private String id;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private VideoStatus status = VideoStatus.DRAFT;
}
