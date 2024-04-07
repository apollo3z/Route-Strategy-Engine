package com.marcura.routestrategyengine.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcura.routestrategyengine.persistance.RouteDocument;
import com.marcura.routestrategyengine.persistance.RouteRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Profile("dev")
public class RouteConsumer {
    private static final Logger logger = LogManager.getLogger("RouteConsumer");

    private final RouteRepository routeRepository;

    private final ObjectMapper objectMapper;

    public RouteConsumer(RouteRepository routeRepository, ObjectMapper objectMapper) {
        this.routeRepository = routeRepository;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "routes", groupId = "rse_routes", containerFactory = "kafkaListenerContainerFactory")
    public void consume(String message) {
        try {
            RouteDocument route = objectMapper.readValue(message, RouteDocument.class);
            if (route != null && route.getPoints() != null && isRouteValid(route)) {
                routeRepository.save(route);
                logger.info("Saved route with ID: " + route.getRouteId());
            } else {
                logger.warn("Received invalid route data, ignoring message.");
            }
        } catch (JsonProcessingException e) {
            logger.error("Error deserializing the message: " + e.getMessage());
        }
    }

    private boolean isRouteValid(RouteDocument route) {
        return route.getPoints().stream().noneMatch(Objects::isNull);
    }
}
