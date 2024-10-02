package com.tin.springcoursera.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JoinCourseRequest {
    @NotNull
    private int courseId;
}
