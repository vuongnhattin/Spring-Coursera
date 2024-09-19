package com.tin.springcoursera.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ListResponse<T> {
    List<T> data;
}
