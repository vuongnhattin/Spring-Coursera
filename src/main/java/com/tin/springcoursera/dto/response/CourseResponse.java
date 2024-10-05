package com.tin.springcoursera.dto.response;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.tin.springcoursera.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CourseResponse {
    @JsonUnwrapped
    private Course course;
    private boolean member;
    private boolean admin;
}
