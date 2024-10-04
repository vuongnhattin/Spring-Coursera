package com.tin.springcoursera.entity;

import lombok.*;
import org.keycloak.representations.idm.UserRepresentation;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Users {
    private String id;
    private String email;
    private String firstName;
    private String lastName;

    public static Users fromRepresentation(UserRepresentation user) {
        return Users.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
}
