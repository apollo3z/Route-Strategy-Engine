package com.marcura.routestrategyengine.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Point {
    private Double latitude;
    private Double longitude;
    private Long timestamp;
    private Double speed;
}
