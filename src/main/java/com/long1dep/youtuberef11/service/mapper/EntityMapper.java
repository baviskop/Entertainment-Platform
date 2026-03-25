package com.long1dep.youtuberef11.service.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

public interface EntityMapper<D, E> {
    @BeanMapping(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    E toEntity(D dto);
    @BeanMapping(unmappedSourcePolicy = ReportingPolicy.IGNORE)
    D toDto(E entity);

    @BeanMapping(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    List<E> toEntity(List<D> dtos);

    List<D> toDto(List<E> entities);
}
