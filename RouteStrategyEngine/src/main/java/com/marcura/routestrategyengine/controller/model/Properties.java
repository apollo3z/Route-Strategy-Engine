package com.marcura.routestrategyengine.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Properties {
    private String id;
    private String from;
    private String to;
    private String vesselId;
    private String stroke;
    private String opacity;
}
