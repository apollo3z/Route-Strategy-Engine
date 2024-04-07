package com.marcura.routestrategyengine.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@AllArgsConstructor
@Data
public class Geometry {
    private String type;
    private List<List<Double>> coordinates;
}
