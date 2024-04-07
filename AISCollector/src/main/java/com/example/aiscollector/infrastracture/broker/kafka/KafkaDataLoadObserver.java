package com.example.aiscollector.infrastracture.broker.kafka;

import com.example.aiscollector.infrastracture.DataLoadObserver;
import com.example.aiscollector.infrastracture.broker.RouteService;
import com.example.aiscollector.infrastracture.broker.kafka.model.Route;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KafkaDataLoadObserver implements DataLoadObserver<Route> {

    private final RouteService routeService;

    public KafkaDataLoadObserver(RouteService routeService) {
        this.routeService = routeService;
    }

    @Override
    public void onDataLoadComplete(List<Route> routes) {
        routeService.send(routes);
    }
}
