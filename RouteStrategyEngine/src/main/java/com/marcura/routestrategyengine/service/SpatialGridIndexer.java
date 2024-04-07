package com.marcura.routestrategyengine.service;

import com.marcura.routestrategyengine.service.model.Route;
import com.marcura.routestrategyengine.service.utils.SpatialGridUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class SpatialGridIndexer {
    public Map<String, Integer> indexPointsBySpatialGrid(List<Route> routeGroup) {
        return routeGroup
                .stream()
                .flatMap(route -> route.getPoints().stream())
                .map(SpatialGridUtils::createGridIndex)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(e -> 1)));
    }
}