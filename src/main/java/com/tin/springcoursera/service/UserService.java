package com.tin.springcoursera.service;

import com.tin.springcoursera.dto.request.UpdateUserRequest;
import com.tin.springcoursera.entity.Users;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final AdminClientService adminClientService;

    public Users findById(String userId) {
        return adminClientService.findById(userId);
    }

    public Users updateUser(String id, UpdateUserRequest request) {
        return adminClientService.updateUser(id, request);
    }
}
