package com.example.aiscollector.infrastracture.broker.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// should depend on more generic Data Model.

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Route {

    private String routeId;

    private Integer fromSeq;

    private Integer toSeq;

    private String fromPort;

    private String toPort;

    private Long legDuration;

    private Integer count;

    private List<Point> points;
}
