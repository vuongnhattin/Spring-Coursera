package com.tin.springcoursera.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateModuleRequest {
    @NotBlank(message = "Tên khoá học không thể rỗng")
    private String name;
    @NotNull(message = "courseId không thể rỗng")
    private Integer courseId;
}
