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
    @NotBlank
    private String name;
    @NotNull
    private Integer moduleNo;
    @NotNull
    private Integer courseId;
}
