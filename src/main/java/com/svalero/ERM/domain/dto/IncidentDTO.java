package com.svalero.ERM.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncidentDTO {
    String location;
    double lat;
    double lon;
    int status;
    String description;
    LocalDate startTime;

}
