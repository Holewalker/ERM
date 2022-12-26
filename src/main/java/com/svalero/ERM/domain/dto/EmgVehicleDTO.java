package com.svalero.ERM.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmgVehicleDTO {
    long emgServiceId;
    String model;
    String vin;
    String type;
    int operStatus;
    boolean onRoute;
    boolean avaliable;
    String location;
    double lat;
    double lon;
    LocalDate lastMaintenance;
}
