package com.marcura.routestrategyengine.service;

import com.marcura.routestrategyengine.service.model.Route;
import com.marcura.routestrategyengine.service.model.RouteCandidate;
import com.marcura.routestrategyengine.persistance.RouteDocument;
import com.marcura.routestrategyengine.service.model.RouteGeoData;
import com.marcura.routestrategyengine.persistance.RouteRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class RouteService {
    private final RouteProcessingService processingService;
    private final RouteDataService dataService;

    public List<RouteGeoData> findMostRepresentativeRoutes(String from, String to) {
        return getRouteGeoData(dataService.routesBy(from, to));
    }

    private List<RouteGeoData> getRouteGeoData(List<Route> filteredRoutes) {
        Map<String, List<Route>> routesGroupedByRouteId = processingService.groupRoutesByRouteId(filteredRoutes);
        List<RouteCandidate> routeCandidates = processingService.evaluateRouteCandidates(routesGroupedByRouteId);
        return processingService.selectMostRepresentativeRoutes(routeCandidates, 1);
    }

}
