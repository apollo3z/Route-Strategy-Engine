package com.marcura.routestrategyengine.consumer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteKafka {

    private String routeId;

    private Integer fromSeq;

    private Integer toSeq;

    private String fromPort;

    private String toPort;

    private Long legDuration;

    private Integer count;

    private List<PointKafka> points;

    @Override
    public String toString() {
        return "RouteKafka{" +
                "routeId='" + routeId + '\'' +
                ", fromSeq=" + fromSeq +
                ", toSeq=" + toSeq +
                ", fromPort='" + fromPort + '\'' +
                ", toPort='" + toPort + '\'' +
                ", legDuration=" + legDuration +
                ", count=" + count +
                '}';
    }
}
