package com.tin.springcoursera.service;

import com.tin.springcoursera.entity.Users;
import com.tin.springcoursera.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public boolean exists(String userId) {
        Optional<Users> user = userRepository.findByUserId(userId);
        return user.isPresent();
    }

    public Users createUser(Users user) {
        return userRepository.save(user);
    }

    public Users findByUserId(String userId) {
        return userRepository.findByUserId(userId).get();
    }

    public Users fromJwt(Jwt jwt) {
        return Users.builder()
                .userId(jwt.getClaimAsString("sub"))
                .email(jwt.getClaimAsString("email"))
                .fullName(jwt.getClaimAsString("name"))
                .build();
    }
}
