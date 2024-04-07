package com.example.aiscollector.infrastracture.loaders.csv.model;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CSVRoute {
    @CsvBindByName(column="id")
    private String routeId;
    @CsvBindByName(column="from_seq")
    private Integer fromSeq;
    @CsvBindByName(column="to_seq")
    private Integer toSeq;
    @CsvBindByName(column="from_port")
    private String fromPort;
    @CsvBindByName(column="to_port")
    private String toPort;
    @CsvBindByName(column="leg_duration")
    private Long legDuration;
    @CsvBindByName(column="count")
    private Integer count;
    @CsvBindByName(column="points")
    private String pointsRaw;
}
