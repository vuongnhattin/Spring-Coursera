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
public class UpdateMaterialRequest {
    @NotBlank
    private String name;
    @NotNull
    private String fileType;
    private MultipartFile file;
}