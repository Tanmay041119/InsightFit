package com.fitness.userservice.repository;

import com.fitness.userservice.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByEmail(@NotBlank(message = "Email is required") @Email(message = "Email should be valid") String email);
    boolean existsById(@NotBlank(message = "User ID is required") String userId);
    Boolean existsByKeycloakId(String keycloakId);
    User findByEmail(@NotBlank(message = "Email is required") @Email(message = "Email should be valid") String email);
    // Additional query methods can be defined here if needed
}
