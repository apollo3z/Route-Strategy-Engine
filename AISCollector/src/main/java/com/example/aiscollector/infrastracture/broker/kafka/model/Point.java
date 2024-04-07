package com.example.aiscollector.infrastracture.broker.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Point {
    private Double latitude;
    private Double longitude;
    private Long timestamp;
    private Double speed;
}
