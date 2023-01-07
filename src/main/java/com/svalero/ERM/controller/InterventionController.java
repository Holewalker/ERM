package com.svalero.ERM.controller;

import com.svalero.ERM.domain.Intervention;
import com.svalero.ERM.domain.dto.InterventionDTO;
import com.svalero.ERM.exception.EmgServiceNotFoundException;
import com.svalero.ERM.exception.IncidentNotFoundException;
import com.svalero.ERM.exception.InterventionNotFoundException;
import com.svalero.ERM.service.InterventionService;
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
public class InterventionController {

    @Autowired
    InterventionService interventionService;

    private final Logger logger = LoggerFactory.getLogger(InterventionController.class);

    @GetMapping("/Interventions")
    public ResponseEntity<List<Intervention>> getInterventions(@RequestParam Map<String, String> data) throws InterventionNotFoundException {
        logger.info("GET Intervention");

        if (data.isEmpty()) {
            logger.info("No more data (Intervention)");
            return ResponseEntity.ok(interventionService.findAll());
        } else if (data.containsKey("status")) {
            logger.info("status:" + data.get("status"));
            List<Intervention> interventionList = interventionService.findByStatus(Integer.parseInt(data.get("status")));
            logger.info("No more data (Intervention)");
            return ResponseEntity.ok(interventionList);
        }  else if (data.containsKey("id")) {
            logger.info("id:" + data.get("id"));
            List<Intervention> interventionList = new ArrayList<>();
            interventionList.add(interventionService.findById(Long.parseLong(data.get("id"))));
            logger.info("No more data (Intervention)");
            return ResponseEntity.ok(interventionList);
        }

        logger.info("Get Intervention Bad request");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/Interventions")
    public ResponseEntity<Intervention> addIntervention(@Valid @RequestBody InterventionDTO interventionDTO) throws EmgServiceNotFoundException, IncidentNotFoundException {
        logger.info("POST Intervention");
        Intervention intervention = interventionService.addIntervention(interventionDTO);
        logger.info("POST Intervention END");
        return ResponseEntity.status(HttpStatus.OK).body(intervention);
    }

    @PutMapping("/Interventions/{id}")
    public ResponseEntity<Intervention> modifyIntervention(@PathVariable long id, @Valid @RequestBody InterventionDTO interventionDTO) throws InterventionNotFoundException, EmgServiceNotFoundException, IncidentNotFoundException {
        logger.info("PUT Intervention");
        Intervention intervention = interventionService.modifyIntervention(id, interventionDTO);
        logger.info("PUT Intervention END");
        return ResponseEntity.status(HttpStatus.OK).body(intervention);
    }

    @DeleteMapping("Interventions/{id}")
    public ResponseEntity<Void> deleteIntervention(@PathVariable long id) throws InterventionNotFoundException {
        logger.info("DELETE Intervention");
        interventionService.deleteIntervention(id);
        logger.info("DELETE Intervention END");
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorUtil> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        logger.error("Data is not correct");
        return getErrorExceptionResponseEntity(methodArgumentNotValidException);
    }

    @ExceptionHandler(InterventionNotFoundException.class)
    public ResponseEntity<ErrorUtil> handleInterventionNotFoundException(InterventionNotFoundException enf) {
        ErrorUtil errorException = new ErrorUtil(404, enf.getMessage());
        logger.info("Intervention not found");

        return new ResponseEntity<>(errorException, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorUtil> handleException(Exception e) {
        ErrorUtil error = new ErrorUtil(500, "Oops, something went wrong");
        logger.info("ERROREXCEPTION:" + e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
