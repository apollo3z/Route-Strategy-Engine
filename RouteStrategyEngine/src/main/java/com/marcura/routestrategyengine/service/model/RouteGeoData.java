package com.marcura.routestrategyengine.service.model;

import com.marcura.routestrategyengine.controller.model.Coordinate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RouteGeoData {
    private String id;
    private String from;
    private String to;
    private String vesselId;
    private List<Coordinate> coordinates;

    public static RouteGeoData from(Route route) {
        return new RouteGeoData(
                route.getFromSeq() + "_" + route.getToSeq(),
                route.getFromPort(),
                route.getToPort(),
                route.getRouteId(),
                coordinatesFrom(route.getPoints())
        );
    }

    public static List<Coordinate> coordinatesFrom(List<Point> points){
        return points.stream()
                .sorted(Comparator.comparing(Point::getTimestamp))
                .map(p -> new Coordinate(p.getLatitude(), p.getLongitude()))
                .collect(Collectors.toList());
    }
}
