package com.marcura.routestrategyengine.service.utils;

import com.marcura.routestrategyengine.service.model.Point;

public class SpatialGridUtils {

    private static final double THRESHOLD = 0.01;

    public static String createGridIndex(Point point) {
        return String.format("%s:%s", mapCoordinate(point.getLatitude()), mapCoordinate(point.getLongitude()));
    }

    private static int mapCoordinate(double coordinate) {
        return (int) (coordinate / THRESHOLD);
    }
}
