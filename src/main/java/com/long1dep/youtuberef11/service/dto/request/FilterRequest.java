package com.long1dep.youtuberef11.service.dto.request;

import lombok.Data;
import org.hibernate.annotations.SecondaryRow;
import org.springframework.data.jpa.domain.Specification;

@Data
public abstract class FilterRequest<T> {
    private PagingRequest paging = new PagingRequest();

    public abstract Specification<T> specification();
}
