package com.fitness.aiservice.service;

import com.fitness.aiservice.model.Activity;
import com.fitness.aiservice.model.Recommendation;
import com.fitness.aiservice.repository.RecommendationRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityListener {
    RabbitListener rabbitListener;
    private final ActivityAIService activityAIService;
    private final RecommendationRepo recommendationRepo;
    // This service listens to activity events and processes them accordingly.
    @RabbitListener(queues = "activity.queue")
    public void listenToActivityEvents(Activity activity) {
        log.info("Received activity event: {}", activity);
//        Recommendation recommendation= activityAIService.getAiResponse(activity);
      //  log.info("Received activity recommendation: {}", activityAIService.getAiResponse(activity));
        Recommendation recommendation=activityAIService.generateRecommendation(activity);

        recommendationRepo.save(recommendation);

    }
}
