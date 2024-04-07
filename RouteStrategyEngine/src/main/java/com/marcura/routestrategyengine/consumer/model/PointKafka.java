package com.marcura.routestrategyengine.consumer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PointKafka {
    private Double latitude;
    private Double longitude;
    private Long timestamp;
    private Double speed;
}
