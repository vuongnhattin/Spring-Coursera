//package com.tin.springcoursera.service;
//
//import com.tin.springcoursera.dto.request.UpdateUserRequest;
//import com.tin.springcoursera.entity.Users;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.keycloak.admin.client.Keycloak;
//import org.keycloak.admin.client.resource.UserResource;
//import org.keycloak.representations.idm.UserRepresentation;
//import org.springframework.stereotype.Service;
//
//import javax.ws.rs.NotFoundException;
//
//@Service
//@AllArgsConstructor
//@Slf4j
//public class AdminClientService {
//    private final Keycloak keycloak;
//    private static final String REALM_NAME = "coursera-realm";
//
//    public Users findById(String id) {
//        try {
//            UserResource userResource = keycloak.realm(REALM_NAME).users().get(id);
//            return Users.fromRepresentation(userResource.toRepresentation());
//        } catch (NotFoundException e) {
//            return new Users();
//        }
//    }
//
//    public Users updateUser(String id, UpdateUserRequest request) {
//        UserResource userResource = keycloak.realm(REALM_NAME).users().get(id);
//        UserRepresentation user = userResource.toRepresentation();
//        user.setFirstName(request.getFirstName());
//        user.setLastName(request.getLastName());
//
//        userResource.update(user);
//
//        return Users.fromRepresentation(userResource.toRepresentation());
//    }
//}
