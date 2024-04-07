package com.marcura.routestrategyengine.controller.model;

import com.marcura.routestrategyengine.service.model.RouteGeoData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeoFeature {
    private String type;
    private Geometry geometry;
    private Properties properties;

    public static GeoFeature from(RouteGeoData route) {
        List<List<Double>> coordinates = route.getCoordinates()
                .stream()
                .map(c -> List.of(c.getLatitude(), c.getLongitude()))
                .collect(Collectors.toList());
        return new GeoFeature(
            "Feature",
            new Geometry("LineString", coordinates),
            new Properties(route.getId(), route.getFrom(), route.getTo(), route.getVesselId(), "red", "0.3")
        );
    }
}

//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class GeoFeature {
//    private String type;
//    private Geometry geometry;
//    private Properties properties;
//
//    public static GeoFeature from(RouteGeoData route) {
//        List<List<Double>> coordinates = route.getCoordinates()
//                .stream()
//                .map(c -> List.of(c.getLatitude(), c.getLongitude()))
//                .collect(Collectors.toList());
//        return new GeoFeature(
//                "Feature",
//                new Geometry("LineString", coordinates),
//                new Properties(route.getId(), route.getFrom(), route.getTo(), route.getVesselId(), "red", "0.3")
//        );
//    }