package com.marcura.routestrategyengine.service;

import com.marcura.routestrategyengine.service.model.Point;
import com.marcura.routestrategyengine.service.model.Route;
import com.marcura.routestrategyengine.service.model.RouteCandidate;
import com.marcura.routestrategyengine.service.model.RouteGeoData;
import com.marcura.routestrategyengine.service.utils.SpatialGridUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RouteProcessingService {
    private final SpatialGridIndexer spatialGridIndexer;

    public RouteProcessingService(SpatialGridIndexer spatialGridIndexer) {
        this.spatialGridIndexer = spatialGridIndexer;
    }

    public Map<String, List<Route>> groupRoutesByRouteId(List<Route> routes) {
        return routes.stream().collect(Collectors.groupingBy(Route::getRouteId));
    }

    public List<RouteCandidate> evaluateRouteCandidates(Map<String, List<Route>> groupedRoutes) {
        return groupedRoutes.values().stream()
                .flatMap(this::evaluateRouteGroup)
                .collect(Collectors.toList());
    }

    private Stream<RouteCandidate> evaluateRouteGroup(List<Route> routeGroup) {
        Map<String, Integer> vesselSpatialGrid = spatialGridIndexer.indexPointsBySpatialGrid(routeGroup);
        return routeGroup.stream().map(route -> evaluateRoute(route, vesselSpatialGrid));
    }

    private RouteCandidate evaluateRoute(Route route, Map<String, Integer> grid) {
        double density = calculateRouteDensity(route.getPoints(), grid);
        long duration = route.getLegDuration();
        return new RouteCandidate(route, density, duration);
    }

    private double calculateRouteDensity(List<Point> points, Map<String, Integer> grid) {
        return points.stream()
                .mapToDouble(point -> grid.getOrDefault(SpatialGridUtils.createGridIndex(point), 0))
                .average()
                .orElse(0);
    }

    public List<RouteGeoData> selectMostRepresentativeRoutes(List<RouteCandidate> candidates, int limit) {
        return candidates.stream()
                .sorted(Comparator.comparing(RouteCandidate::getDensity).thenComparing(RouteCandidate::getDuration).reversed())
                .limit(limit)
                .map(RouteCandidate::getRoute)
                .map(RouteGeoData::from)
                .collect(Collectors.toList());
    }
}
