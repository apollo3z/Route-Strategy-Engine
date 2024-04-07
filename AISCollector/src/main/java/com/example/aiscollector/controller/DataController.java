package com.example.aiscollector.controller;

import com.example.aiscollector.infrastracture.broker.kafka.model.Route;
import com.example.aiscollector.infrastracture.loaders.csv.CSVLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/routes")
public class DataController {

    private final CSVLoader loader;

    public DataController(CSVLoader loader) {
        this.loader = loader;
    }

    @GetMapping()
    public ResponseEntity<List<Route>> getMostRepresentativeRoutes() {
        return ResponseEntity.ok(loader.load());
    }
}
