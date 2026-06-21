package com.long1dep.youtuberef11.service.dto.request;

import com.long1dep.youtuberef11.entity.VideoEntity;
import com.long1dep.youtuberef11.entity.enums.VideoStatus;
import com.long1dep.youtuberef11.repository.specification.VideoSpecification;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VideoSearchRequest extends FilterRequest<VideoEntity> {
    String url;
    String description;
    List<VideoStatus> status = new ArrayList<>();

    @Override
    public Specification<VideoEntity> specification() {
        return VideoSpecification.builder()
                .withUrl(this.url)
                .withDescription(this.description)
                .withStatuses(this.status)
                .build();
    }
}
