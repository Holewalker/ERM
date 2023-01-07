package com.svalero.ERM.service;

import com.svalero.ERM.domain.Intervention;
import com.svalero.ERM.domain.Report;
import com.svalero.ERM.domain.dto.ReportDTO;
import com.svalero.ERM.exception.EmgPersonalNotFoundException;
import com.svalero.ERM.exception.InterventionNotFoundException;
import com.svalero.ERM.exception.ReportNotFoundException;
import com.svalero.ERM.repository.InterventionRepository;
import com.svalero.ERM.repository.ReportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;
    InterventionRepository interventionRepository;

    private final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

    @Override
    public List<Report> findAll() {
        return reportRepository.findAll();
    }

    @Override
    public Report findById(long id) throws ReportNotFoundException {
        logger.info("ID Report: " + id);
        return reportRepository.findById(id).orElseThrow(ReportNotFoundException::new);
    }

   
    @Override
    public List<Report> findByTitle(String title) {
        logger.info("Report title: " + title);
        return reportRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public List<Report> findByEndDate(LocalDate endDate) {
        logger.info("Report End Date: " + endDate);
        return reportRepository.findByEndDate(endDate);
    }

    @Override
    public Report addReport(ReportDTO reportDTO) throws  InterventionNotFoundException {
        logger.info("Creating Report " + reportDTO);
        Report newReport = new Report();
        Intervention intervention = interventionRepository.findById(reportDTO.getInterventionId()).orElseThrow(InterventionNotFoundException::new);
        newReport.setInterventionReport(intervention);
        newReport.setEndDate(reportDTO.getEndDate());
        newReport.setTitle(reportDTO.getTitle());
        newReport.setText(reportDTO.getText());
        newReport.setReporterComment(reportDTO.getReporterComment());
        return reportRepository.save(newReport);
    }

    @Override
    public void deleteReport(long id) throws ReportNotFoundException {
        logger.info("deleting Report " + id);
        Report report = reportRepository.findById(id).orElseThrow(ReportNotFoundException::new);
        logger.info(" " + report);
        reportRepository.delete(report);
    }

    @Override
    public Report modifyReport(long id, ReportDTO reportDTO) throws ReportNotFoundException {
        Report currentReport = reportRepository.findById(id).orElseThrow(ReportNotFoundException::new);
        logger.info("Changing Report " + id + currentReport);
        currentReport.setEndDate(reportDTO.getEndDate());
        currentReport.setTitle(reportDTO.getTitle());
        currentReport.setText(reportDTO.getText());
        currentReport.setReporterComment(reportDTO.getReporterComment());
        logger.info("Report Changed " + id + reportDTO);
        return reportRepository.save(currentReport);
    }


}
