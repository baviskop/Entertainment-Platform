package com.long1dep.youtuberef11.entity;

import com.long1dep.youtuberef11.entity.enums.VideoStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "video")
@SQLRestriction("status != 'DELETED'")
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

    @Column(name = "thumbnail", columnDefinition = "TEXT")
    private String thumbnail;

    @Column(name = "views")
    private Long views = 0L;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private VideoStatus status = VideoStatus.DRAFT;
}
