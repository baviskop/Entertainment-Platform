package com.long1dep.youtuberef11.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "video")
@Getter
@Setter
public class VideoEntity extends AbstractAuditingEntity<String>{
    @Id
    @UuidGenerator
    private String id;

    @Column(name = "url",  nullable = false)
    private String url;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
}
