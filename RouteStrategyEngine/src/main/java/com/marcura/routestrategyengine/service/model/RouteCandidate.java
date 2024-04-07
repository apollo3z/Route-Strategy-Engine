package com.marcura.routestrategyengine.service.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RouteCandidate {
    private Route route;
    private double density;
    private long duration;

    public RouteCandidate(Route route, double density, long duration) {
        this.route = route;
        this.density = density;
        this.duration = duration;
    }
}