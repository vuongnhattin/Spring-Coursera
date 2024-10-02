package com.tin.springcoursera.controller;

import com.tin.springcoursera.entity.Users;
import com.tin.springcoursera.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("me")
    public Users createUser(@AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getClaimAsString("sub");
        if (userService.exists(userId)) {
            return null;
        }

        Users user = userService.fromJwt(jwt);

        return userService.createUser(user);
    }

    @GetMapping("me")
    public Users getUser(@AuthenticationPrincipal Jwt jwt) {
        return userService.fromJwt(jwt);
    }
}
