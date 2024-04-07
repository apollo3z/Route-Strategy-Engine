package com.marcura.routestrategyengine.service;

import com.marcura.routestrategyengine.persistance.RouteRepository;
import com.marcura.routestrategyengine.service.model.Route;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RouteDataService {
    private static final Logger logger = LogManager.getLogger("RouteDataService");

    private final RouteRepository routeRepository;

    private final AisCollectorClient aisCollectorClient;

    private final Environment environment;

    public List<Route> routesBy(String from, String to) {
        return (isLocalProfileActive() ? fetchRoutes(from, to) : queryRoutes(from, to))
                .orElseGet(() -> {
                    logger.info("No routes found");
                    return Collections.emptyList();
                });
    }

    private boolean isLocalProfileActive() {
        return Arrays.asList(environment.getActiveProfiles()).contains("local");
    }

    private Optional<List<Route>> fetchRoutes(String from, String to) {
        return Optional.of(
                Objects.requireNonNull(aisCollectorClient.getMostRepresentativeRoutes().getBody())
                        .stream()
                        .filter(route -> matchesFromToCriteria(route, from, to))
                        .collect(Collectors.toList())
        );
    }

    private Optional<List<Route>> queryRoutes(String from, String to) {
        return Optional.of(routeRepository.findByFromPortAndToPort(from, to).stream()
                .map(Route::from)
                .collect(Collectors.toList()));
    }

    private boolean matchesFromToCriteria(Route route, String from, String to) {
        return (from == null || route.getFromPort().equals(from)) &&
                (to == null || route.getToPort().equals(to));
    }
}
