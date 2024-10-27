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
    private List<T> data;
    private int page;
    private int pageSize;
    private int totalPages;
    private long totalElements;

    public static <T> PageResponse<T> from(Page<T> page) {
        return new PageResponse<>(page.getContent(), page.getNumber(), page.getSize(), page.getTotalPages(), page.getTotalElements());
    }
}
