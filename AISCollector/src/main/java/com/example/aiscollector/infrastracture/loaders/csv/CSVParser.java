package com.example.aiscollector.infrastracture.loaders.csv;

import com.example.aiscollector.infrastracture.FailedRecordService;
import com.example.aiscollector.infrastracture.broker.kafka.model.Point;
import com.example.aiscollector.infrastracture.broker.kafka.model.Route;
import com.example.aiscollector.infrastracture.loaders.Parser;
import com.example.aiscollector.infrastracture.loaders.csv.model.CSVRoute;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CSVParser implements Parser<Route, CSVRoute> {
    private static final Logger logger = LogManager.getLogger("CSVParser");
    private final ObjectMapper mapper = new ObjectMapper();

    private final FailedRecordService failedRecordService;

    public CSVParser(FailedRecordService failedRecordService) {
        this.failedRecordService = failedRecordService;
    }

    @Override
    public Route parse(CSVRoute data) {
        try {
            List<List<Object>> rawPoints = transformRawPoints(data.getPointsRaw());
            List<Point> parsedPoints = parsePoints(rawPoints, data.getRouteId());
            return toRoute(data, parsedPoints);
        } catch (JsonProcessingException e) {
            logger.error("Error parsing CSVRoute, saving failed record. " + e.getMessage());
            failedRecordService.handleFailedRecord(data.getRouteId(), "Error parsing CSVRoute");
            return null;
        }
    }

    private List<List<Object>> transformRawPoints(String rawPoints) throws JsonProcessingException {
        return mapper.readValue(rawPoints, new TypeReference<>() {});
    }

    private Route toRoute(CSVRoute csvRoute, List<Point> points) {
        Route route = new Route();
        route.setRouteId(csvRoute.getRouteId());
        route.setFromSeq(csvRoute.getFromSeq());
        route.setToSeq(csvRoute.getToSeq());
        route.setFromPort(csvRoute.getFromPort());
        route.setToPort(csvRoute.getToPort());
        route.setLegDuration(csvRoute.getLegDuration());
        route.setCount(csvRoute.getCount());
        route.setPoints(points);
        return route;
    }

    private List<Point> parsePoints(List<List<Object>> points, String routeId) {
        return points.stream()
                .map(pointRaw -> toPoint(pointRaw, routeId))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private Optional<Point> toPoint(List<Object> rawPoint, String routeId) {
        try {
            Double longitude = Optional.ofNullable(rawPoint.get(0)).map(String::valueOf).map(Double::parseDouble).orElse(null);
            Double latitude = Optional.ofNullable(rawPoint.get(1)).map(String::valueOf).map(Double::parseDouble).orElse(null);
            Long timestamp = Optional.ofNullable(rawPoint.get(2)).map(String::valueOf).map(Long::parseLong).orElse(null);
            Double speed = Optional.ofNullable(rawPoint.get(3)).map(String::valueOf).map(Double::parseDouble).orElse(null);
            if (latitude == null || longitude == null || timestamp == null) {
                throw new IllegalArgumentException("Incomplete point data for routeId: " + routeId);
            }
            return Optional.of(new Point(longitude, latitude, timestamp, speed));
        } catch (Exception e) {
            logger.error("Failed to parse point for routeId: " + routeId + ", error: " + e.getMessage());
            failedRecordService.handleFailedRecord(routeId, "Failed to parse point: " + e.getMessage());
            return Optional.empty();
        }
    }
}
