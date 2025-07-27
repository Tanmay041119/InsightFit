package com.fitness.userservice.controllers;

import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.services.userService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class userController {

    @Autowired
    private userService userService;
    @GetMapping("/{userid}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable String userid) {
        // Logic to retrieve user by ID
        return ResponseEntity.ok(userService.getUserProfile(userid));
    }
    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody RegisterRequest userRequest) {
        // Logic to register a new user
        return ResponseEntity.ok(userService.register(userRequest));
    }
    @GetMapping("/{userid}/validate")
    public ResponseEntity<Boolean> validateUser(
            @PathVariable String userid,
            @RequestHeader(value = "X-User-ID", required = false) String keycloakId) { // Added required = false

//        // Add logging to see the value
//        System.out.println("Received X-User-ID header: " + keycloakId);
//
//        if (keycloakId == null || keycloakId.isEmpty()) {
//            // Handle scenario where header is missing/empty, maybe return 400 or false
//            System.err.println("X-User-ID header is missing or empty!");
//            return ResponseEntity.badRequest().body(false); // Or throw a specific exception
//        }
        return ResponseEntity.ok(userService.existByKeycloakId(keycloakId));
    }

}
