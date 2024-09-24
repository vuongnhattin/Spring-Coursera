package com.tin.springcoursera.dto.request;

import com.tin.springcoursera.entity.Course;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequest {
    @NotBlank(message = "Tên khoá học không thể rỗng")
    private String name;
    @NotBlank(message = "Mô tả khoá học không thể rỗng")
    private String description;
}
