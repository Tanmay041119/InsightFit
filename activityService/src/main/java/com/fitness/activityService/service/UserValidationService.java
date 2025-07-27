package com.fitness.activityService.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserValidationService {
    private final WebClient userServiceWebClient;

    public Boolean validate(String userId) {
        log.info("Checking if user exists with ID: {}",userId);
        try {
            return userServiceWebClient.get()
                    .uri("/api/users/{userId}/validate", userId) // Corrected URI here
                    .header("X-User-ID", userId) // Assuming you want to pass the userId as a header
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
        } catch (WebClientResponseException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new RuntimeException("User not found: " + userId);
            } else if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new RuntimeException("Invalid user ID: " + userId);
            } else {
                // Log the actual status code and response body for better debugging
                System.err.println("Error validating user. Status: " + e.getStatusCode() + ", Body: " + e.getResponseBodyAsString());
                throw new RuntimeException("Error validating user: " + e.getMessage());
            }
        } catch (WebClientRequestException e) {
            // This catches connection issues, DNS resolution failures, etc.
            throw new RuntimeException("Failed to connect to user-service: " + e.getMessage(), e);
        } catch (Exception e) {
            // Catch any other unexpected exceptions
            throw new RuntimeException("An unexpected error occurred during user validation: " + e.getMessage(), e);
        }
    }
}