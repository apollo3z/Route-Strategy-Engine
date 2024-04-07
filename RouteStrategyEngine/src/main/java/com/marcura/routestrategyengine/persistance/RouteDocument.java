package com.marcura.routestrategyengine.persistance;

import com.marcura.routestrategyengine.service.model.Point;
import com.marcura.routestrategyengine.service.model.Route;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "routes")
@NoArgsConstructor
@AllArgsConstructor
public class RouteDocument {

    @Id
    private String id;
    private String routeId;
    private Integer fromSeq;
    private Integer toSeq;
    private String fromPort;
    private String toPort;
    private Long legDuration;
    private Integer count;
    private List<Point> points;

    public RouteDocument(Route r) {
        this.routeId = r.getRouteId();
        this.fromSeq = r.getFromSeq();
        this.toSeq = r.getToSeq();
        this.fromPort = r.getFromPort();
        this.toPort = r.getToPort();
        this.legDuration = r.getLegDuration();
        this.count = r.getCount();
        this.points = r.getPoints();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public Integer getFromSeq() {
        return fromSeq;
    }

    public void setFromSeq(Integer fromSeq) {
        this.fromSeq = fromSeq;
    }

    public Integer getToSeq() {
        return toSeq;
    }

    public void setToSeq(Integer toSeq) {
        this.toSeq = toSeq;
    }

    public String getFromPort() {
        return fromPort;
    }

    public void setFromPort(String fromPort) {
        this.fromPort = fromPort;
    }

    public String getToPort() {
        return toPort;
    }

    public void setToPort(String toPort) {
        this.toPort = toPort;
    }

    public Long getLegDuration() {
        return legDuration;
    }

    public void setLegDuration(Long legDuration) {
        this.legDuration = legDuration;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }
}
