package com.fitness.aiservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.Map;

@Service
@Slf4j
public class GeminiService {

    private final WebClient webClient;
    private final String geminiApiBaseUrl= "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent"; // Store the base URL

    public GeminiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public String getResponse(String prompt) {
        Map<String, Object> requestBody = Map.of(
                "contents", Collections.singletonList(
                        Map.of("parts", Collections.singletonList(
                                Map.of("text", prompt)
                        ))
                )
        );

        try {
            String geminiApiKey = "YOUR API KEY HERE";
            String response = webClient.post()
                    .uri(geminiApiBaseUrl)
                    .header("Content-Type" , "application/json")
                    .header("X-goog-api-key", geminiApiKey)
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            return response;

        } catch (Exception e) {
            log.error("Error calling Gemini API: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to get response from Gemini API", e);
        }
    }
}