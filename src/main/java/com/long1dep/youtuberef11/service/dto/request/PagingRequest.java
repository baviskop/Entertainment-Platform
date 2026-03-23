package com.long1dep.youtuberef11.service.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hibernate.internal.util.collections.ArrayHelper.forEach;

@Setter
@Getter
public class PagingRequest {
    private int page = 1;
    private int size = 10;
    private Map<String, String> orders = new HashMap<>();
    
    public Pageable pageable() {
        if(CollectionUtils.isEmpty(orders)) {
            return PageRequest.of(page -1, size);
        }
        Sort sortable = sortable(orders);
        return PageRequest.of(page -1, size, sortable);
    }

    private Sort sortable(Map<String, String> orders) {
        List<Sort.Order> sortableList = new ArrayList<>();
        orders.forEach((key, value) -> {
            Sort.Direction direction = Sort.Direction.DESC.name().equals(value) ? Sort.Direction.DESC : Sort.Direction.ASC;
            Sort.Order order = new Sort.Order(direction, key);
            sortableList.add(order);
        });
        return Sort.by(sortableList);
    }
}
