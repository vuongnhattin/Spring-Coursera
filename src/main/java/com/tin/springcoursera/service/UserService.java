package com.tin.springcoursera.service;

import com.tin.springcoursera.dto.request.UpdateUserRequest;
import com.tin.springcoursera.entity.Users;
import com.tin.springcoursera.exception.AppException;
import com.tin.springcoursera.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    public static final String USER_NOT_FOUND = "User not found";
    //    private final AdminClientService adminClientService;
    private final ModelMapper mapper;

    private final UserRepository userRepository;

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public Users findById(Integer userId) {
        return userRepository.findById(userId).orElseThrow(() -> new AppException(404, USER_NOT_FOUND));
    }

    public Users findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new AppException(404, USER_NOT_FOUND));
    }

    public Users updateUser(Integer id, UpdateUserRequest request) {
        Users oldUser = userRepository.findById(id).orElseThrow(() -> new AppException(404, USER_NOT_FOUND));

        mapper.map(request, oldUser);
        return userRepository.save(oldUser);
    }

    public Users getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (Users) authentication.getPrincipal();
    }
}
