package com.tin.springcoursera.controller;


import com.tin.springcoursera.dto.request.LoginRequest;
import com.tin.springcoursera.dto.request.RegisterRequest;
import com.tin.springcoursera.dto.response.LoginResponse;
import com.tin.springcoursera.entity.Users;
import com.tin.springcoursera.service.AuthService;
import com.tin.springcoursera.service.JwtService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("auth")
@RestController
@AllArgsConstructor
public class AuthController {
    private final JwtService jwtService;

    private final AuthService authenticationService;

    @PostMapping("register")
    public Users register(@RequestBody @Valid RegisterRequest registerUserDto) {
        return authenticationService.register(registerUserDto);
    }

    @PostMapping("login")
    public LoginResponse login(@RequestBody @Valid LoginRequest loginUserDto) {
        Users authenticatedUser = authenticationService.login(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        return LoginResponse.builder()
                .token(jwtToken)
                .expiresIn(jwtService.getExpirationTime())
                .build();
    }
}
