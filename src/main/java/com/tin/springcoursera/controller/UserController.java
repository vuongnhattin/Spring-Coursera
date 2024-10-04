package com.tin.springcoursera.controller;

import com.tin.springcoursera.dto.request.UpdateUserRequest;
import com.tin.springcoursera.entity.Users;
import com.tin.springcoursera.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("me")
    public Users getMe(Authentication authentication) {
        return userService.findById(authentication.getName());
    }

    @PutMapping("me")
    public Users updateMe(Authentication authentication, @RequestBody @Valid UpdateUserRequest request) {
        String id = authentication.getName();
        return  userService.updateUser(id, request);
    }
}
