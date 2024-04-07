package com.marcura.routestrategyengine.controller.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeoFeatureCollection {
    private String type;
    private List<GeoFeature> features;
}
