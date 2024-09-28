package com.tin.springcoursera.entity;

import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.security.Principal;

@AllArgsConstructor
public class UserPrincipal implements Principal {
    private String userId;

    @Override
    public String getName() {
        return userId;
    }
}
