package com.svalero.ERM.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO {
    long interventionId;
    LocalDate endDate;
    String title;
    String text;
    String reporterComment;


}
