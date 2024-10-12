package com.tin.springcoursera.dto.response;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.tin.springcoursera.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserCourseResponse {
    @JsonUnwrapped
    private Users user;
    private boolean admin;
}
