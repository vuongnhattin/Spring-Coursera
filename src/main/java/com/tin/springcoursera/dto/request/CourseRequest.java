package com.tin.springcoursera.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;

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
    private String introduction;
    @DecimalMin(value = "0.1", message = "Giá khoá học phải lớn hơn hoặc bằng $0.1")
    private BigDecimal price;
}
