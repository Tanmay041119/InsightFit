package com.fitness.gateway.user;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class UserResponse {
    private String id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String keycloakId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    public void builder(String id, String email, String password, String firstName, String lastName, LocalDateTime createdAt, LocalDateTime updatedAt, String keycloakId) {
        this.setId(id);
        this.setEmail(email);
        this.setPassword(password);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setCreatedAt(createdAt);
        this.setUpdatedAt(updatedAt);
        this.setKeycloakId(keycloakId);
    }
}