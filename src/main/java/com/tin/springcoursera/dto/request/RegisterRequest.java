package com.tin.springcoursera.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    @NotBlank(message = "Tên đăng nhập không được trống")
    private String username;
    @Size(min = 3, message = "Mật khẩu phải có ít nhất 3 ký tự")
    private String password;
    @NotBlank(message = "Họ không được trống")
    private String firstName;
    @NotBlank(message = "Tên không được trống")
    private String lastName;
    @NotBlank(message = "Email không được trống")
    private String email;
}
