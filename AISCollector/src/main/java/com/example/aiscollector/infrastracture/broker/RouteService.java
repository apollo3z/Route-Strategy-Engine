package com.example.aiscollector.infrastracture.broker;

import com.example.aiscollector.infrastracture.broker.kafka.model.Route;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {
    private final MessageProducer<Route> routeProducer;

    public RouteService(MessageProducer<Route> routeProducer) {
        this.routeProducer = routeProducer;
    }

    public void send(List<Route> routes) {
        routes.forEach(route -> {
            String topic = "routes";
            String key = route.getRouteId() + "_" + route.getLegDuration();
            try {
                routeProducer.send(topic, key, route);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }
}