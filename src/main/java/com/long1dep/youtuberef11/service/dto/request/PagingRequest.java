package com.long1dep.youtuberef11.service.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class PagingRequest {
    private int page = 1;
    private int size = 10;
    private Map<String, String> orders = new HashMap<>();

    public Pageable pageable() {
        int pageIndex = Math.max(page - 1, 0);
        if (CollectionUtils.isEmpty(orders)) {
            return PageRequest.of(pageIndex, size);
        }
        Sort sortable = sortable(orders);
        return PageRequest.of(pageIndex, size, sortable);
    }

    public Sort sortable(Map<String, String> orders) {
        List<Sort.Order> sortableList = new ArrayList<>();
        orders.forEach((key, value) -> {
            Sort.Direction direction = Sort.Direction.DESC.name().equals(value) ? Sort.Direction.DESC : Sort.Direction.ASC;
            String mappedKey = "updatedAt".equals(key) ? "lastModifiedDate" : key;
            Sort.Order order = new Sort.Order(direction, mappedKey);
            sortableList.add(order);
        });
        return Sort.by(sortableList);

    }
}
