package com.tin.springcoursera.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class PageResponse<T> {
    List<T> data;
    int page;
    int pageSize;
    int totalPages;
    long totalElements;

    public static <T> PageResponse<T> from(Page<T> page) {
        return new PageResponse<>(page.getContent(), page.getNumber(), page.getSize(), page.getTotalPages(), page.getTotalElements());
    }
}
