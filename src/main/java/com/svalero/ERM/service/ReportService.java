package com.svalero.ERM.service;

import com.svalero.ERM.domain.Report;
import com.svalero.ERM.domain.dto.ReportDTO;
import com.svalero.ERM.exception.EmgPersonalNotFoundException;
import com.svalero.ERM.exception.InterventionNotFoundException;
import com.svalero.ERM.exception.ReportNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {

    List<Report> findAll();

    Report findById(long id) throws ReportNotFoundException;

    List<Report> findByTitle(String title);

    List<Report> findByEndDate(LocalDate endDate);


    Report addReport(ReportDTO reportDTO) throws EmgPersonalNotFoundException, InterventionNotFoundException;

    void deleteReport(long id) throws ReportNotFoundException;

    Report modifyReport(long id, ReportDTO reportDTO) throws ReportNotFoundException;
}
