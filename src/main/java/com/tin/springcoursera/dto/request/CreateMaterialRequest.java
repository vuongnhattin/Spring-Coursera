package com.tin.springcoursera.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateMaterialRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String fileType;
    @NotNull
    private Integer moduleId;
    @NotNull
    private MultipartFile file;
}
