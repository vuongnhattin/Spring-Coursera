package com.tin.springcoursera.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateModuleRequest {
    @NotBlank
    private String name;
    @NotNull
    public int moduleNo;
}
