package com.svalero.ERM.controller;

import com.svalero.ERM.domain.Report;
import com.svalero.ERM.domain.dto.ReportDTO;
import com.svalero.ERM.exception.EmgPersonalNotFoundException;
import com.svalero.ERM.exception.EmgServiceNotFoundException;
import com.svalero.ERM.exception.InterventionNotFoundException;
import com.svalero.ERM.exception.ReportNotFoundException;
import com.svalero.ERM.service.ReportService;
import com.svalero.ERM.util.ErrorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.svalero.ERM.util.ErrorUtil.getErrorExceptionResponseEntity;

@RestController
public class ReportController {

    @Autowired
    ReportService reportService;

    private final Logger logger = LoggerFactory.getLogger(ReportController.class);

    @GetMapping("/Reports")
    public ResponseEntity<List<Report>> getReports(@RequestParam Map<String, String> data) throws ReportNotFoundException {
        logger.info("GET Report");

        if (data.isEmpty()) {
            logger.info("No more data (Report)");
            return ResponseEntity.ok(reportService.findAll());
        } else if (data.containsKey("model")) {
            logger.info("title:" + data.get("title"));
            List<Report> reportList = reportService.findByTitle(data.get("title"));
            logger.info("No more data (Report)");
            return ResponseEntity.ok(reportList);
        } else if (data.containsKey("endDate")) {
            logger.info("endDate:" + data.get("endDate"));
            List<Report> reportList = reportService.findByEndDate(LocalDate.parse(data.get("endDate")));
            logger.info("No more data (Report)");
            return ResponseEntity.ok(reportList);
        } else if (data.containsKey("id")) {
            logger.info("id:" + data.get("id"));
            List<Report> reportList = new ArrayList<>();
            reportList.add(reportService.findById(Long.parseLong(data.get("id"))));
            logger.info("No more data (Report)");
            return ResponseEntity.ok(reportList);
        }

        logger.info("Get Report Bad request");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/Reports")
    public ResponseEntity<Report> addReport(@Valid @RequestBody ReportDTO reportDTO) throws EmgServiceNotFoundException, InterventionNotFoundException, EmgPersonalNotFoundException {
        logger.info("POST Report");
        Report report = reportService.addReport(reportDTO);
        logger.info("POST Report END");
        return ResponseEntity.status(HttpStatus.OK).body(report);
    }

    @PutMapping("/Reports/{id}")
    public ResponseEntity<Report> modifyReport(@PathVariable long id, @Valid @RequestBody ReportDTO reportDTO) throws ReportNotFoundException, EmgServiceNotFoundException {
        logger.info("PUT Report");
        Report report = reportService.modifyReport(id, reportDTO);
        logger.info("PUT Report END");
        return ResponseEntity.status(HttpStatus.OK).body(report);
    }

    @DeleteMapping("Reports/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable long id) throws ReportNotFoundException {
        logger.info("DELETE Report");
        reportService.deleteReport(id);
        logger.info("DELETE Report END");
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorUtil> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        logger.error("Data is not correct");
        return getErrorExceptionResponseEntity(methodArgumentNotValidException);
    }

    @ExceptionHandler(ReportNotFoundException.class)
    public ResponseEntity<ErrorUtil> handleReportNotFoundException(ReportNotFoundException enf) {
        ErrorUtil errorException = new ErrorUtil(404, enf.getMessage());
        logger.info("Report not found");

        return new ResponseEntity<>(errorException, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorUtil> handleException(Exception e) {
        ErrorUtil error = new ErrorUtil(500, "Oops, something went wrong");
        logger.info("ERROREXCEPTION:" + e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
