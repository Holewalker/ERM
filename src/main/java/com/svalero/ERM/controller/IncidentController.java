package com.svalero.ERM.controller;

import com.svalero.ERM.domain.Incident;
import com.svalero.ERM.domain.dto.IncidentDTO;
import com.svalero.ERM.exception.EmgServiceNotFoundException;
import com.svalero.ERM.exception.IncidentNotFoundException;
import com.svalero.ERM.service.IncidentService;
import com.svalero.ERM.util.ErrorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.svalero.ERM.util.ErrorUtil.getErrorExceptionResponseEntity;

@RestController
public class IncidentController {

    @Autowired
    IncidentService incidentService;

    private final Logger logger = LoggerFactory.getLogger(IncidentController.class);

    @GetMapping("/Incidents")
    public ResponseEntity<List<Incident>> getIncidents(@RequestParam Map<String, String> data) throws IncidentNotFoundException {
        logger.info("GET Incident");

        if (data.isEmpty()) {
            logger.info("No more data (Incident)");
            return ResponseEntity.ok(incidentService.findAll());
        } else if (data.containsKey("status")) {
            logger.info("status:" + data.get("status"));
            List<Incident> incidentList = incidentService.findByStatus(Integer.parseInt(data.get("status")));
            logger.info("No more data (Incident)");
            return ResponseEntity.ok(incidentList);
        } else if (data.containsKey("location")) {
            logger.info("location:" + data.get("location"));
            List<Incident> incidentList = incidentService.findByLocation(data.get("location"));
            logger.info("No more data (Incident)");
            return ResponseEntity.ok(incidentList);
        } else if (data.containsKey("id")) {
            logger.info("id:" + data.get("id"));
            List<Incident> incidentList = new ArrayList<>();
            incidentList.add(incidentService.findById(Long.parseLong(data.get("id"))));
            logger.info("No more data (Incident)");
            return ResponseEntity.ok(incidentList);
        }

        logger.info("Get Incident Bad request");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/Incidents")
    public ResponseEntity<Incident> addIncident(@Valid @RequestBody IncidentDTO incidentDTO) throws EmgServiceNotFoundException {
        logger.info("POST Incident");
        Incident incident = incidentService.addIncident(incidentDTO);
        logger.info("POST Incident END");
        return ResponseEntity.status(HttpStatus.OK).body(incident);
    }

    @PutMapping("/Incidents/{id}")
    public ResponseEntity<Incident> modifyIncident(@PathVariable long id, @Valid @RequestBody IncidentDTO incidentDTO) throws IncidentNotFoundException, EmgServiceNotFoundException {
        logger.info("PUT Incident");
        Incident incident = incidentService.modifyIncident(id, incidentDTO);
        logger.info("PUT Incident END");
        return ResponseEntity.status(HttpStatus.OK).body(incident);
    }

    @DeleteMapping("Incidents/{id}")
    public ResponseEntity<Void> deleteIncident(@PathVariable long id) throws IncidentNotFoundException {
        logger.info("DELETE Incident");
        incidentService.deleteIncident(id);
        logger.info("DELETE Incident END");
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorUtil> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        logger.error("Data is not correct");
        return getErrorExceptionResponseEntity(methodArgumentNotValidException);
    }

    @ExceptionHandler(IncidentNotFoundException.class)
    public ResponseEntity<ErrorUtil> handleIncidentNotFoundException(IncidentNotFoundException enf) {
        ErrorUtil errorException = new ErrorUtil(404, enf.getMessage());
        logger.info("Incident not found");

        return new ResponseEntity<>(errorException, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorUtil> handleException(Exception e) {
        ErrorUtil error = new ErrorUtil(500, "Oops, something went wrong");
        logger.info("ERROREXCEPTION:" + e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
