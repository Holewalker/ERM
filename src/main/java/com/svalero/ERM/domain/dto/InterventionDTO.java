package com.svalero.ERM.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterventionDTO {
    long incidentId;
    long emergencyServiceId;
    int status;
    LocalDate dispatchDate;
    LocalDate endDate;

}
