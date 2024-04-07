package com.marcura.routestrategyengine.service.model;

import com.marcura.routestrategyengine.persistance.RouteDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    public Route(RouteDocument routeDocument) {
        this.routeId = routeDocument.getRouteId();
        this.fromSeq = routeDocument.getFromSeq();
        this.toSeq = routeDocument.getToSeq();
        this.fromPort = routeDocument.getFromPort();
        this.toPort = routeDocument.getToPort();
        this.legDuration = routeDocument.getLegDuration();
        this.count = routeDocument.getCount();
        this.points = routeDocument.getPoints();
    }

    public static Route from(RouteDocument routeDocument) {
        return new Route(routeDocument);
    }
}
