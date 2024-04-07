package com.marcura.routestrategyengine.controller;

import com.marcura.routestrategyengine.controller.model.GeoFeature;
import com.marcura.routestrategyengine.controller.model.GeoFeatureCollection;
import com.marcura.routestrategyengine.service.RouteService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/routes")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeProcessingService) {
        this.routeService = routeProcessingService;
    }

    @GetMapping("/representative")
    public ResponseEntity<GeoFeatureCollection> getMostRepresentativeRoutes(
            @RequestParam @NotBlank(message = "Origin port must not be blank") String from,
            @RequestParam @NotBlank(message = "Destination port must not be blank") String to) {
        List<GeoFeature> features = routeService.findMostRepresentativeRoutes(from, to)
                .stream()
                .map(GeoFeature::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new GeoFeatureCollection("FeatureCollection", features));
    }
}