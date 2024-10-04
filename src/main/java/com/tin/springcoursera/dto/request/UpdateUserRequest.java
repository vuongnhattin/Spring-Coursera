package com.tin.springcoursera.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
}
