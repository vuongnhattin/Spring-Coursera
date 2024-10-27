package com.tin.springcoursera.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ListResponse<T> {
    private List<T> data;
}
