package com.svalero.ERM.controller;

import com.svalero.ERM.EmergencyReportingSystemApplication;
import com.svalero.ERM.domain.EmgService;
import com.svalero.ERM.domain.dto.EmgServiceDTO;
import com.svalero.ERM.exception.EmgServiceNotFoundException;
import com.svalero.ERM.exception.ErrorException;
import com.svalero.ERM.service.EmgServiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class EmgServiceController {

    @Autowired
    EmgServiceService emgServiceService;

    private final Logger logger = LoggerFactory.getLogger(EmgServiceController.class);

    @GetMapping("/EmergencyServices")
    public ResponseEntity<List<EmgService>> getEmgServices(@RequestParam Map<String, String> data) throws EmgServiceNotFoundException {
        logger.info("GET Emg Service");

        if (data.isEmpty()) {
            logger.info("No more data (EmgService)");
            return ResponseEntity.ok(emgServiceService.findAll());
        } else if (data.containsKey("location")) {
            logger.info("location:" + data.get("location"));
            List<EmgService> emgServiceList = emgServiceService.findByLocation(data.get("location"));
            logger.info("No more data (EmgService)");
            return ResponseEntity.ok(emgServiceList);
        } else if (data.containsKey("type")) {
            logger.info("type:" + data.get("type"));
            List<EmgService> emgServiceList = emgServiceService.findByType(data.get("type"));
            logger.info("No more data (EmgService)");
            return ResponseEntity.ok(emgServiceList);
        } else if (data.containsKey("id")) {
            logger.info("id:" + data.get("id"));
            List<EmgService> emgServiceList = new ArrayList<>();
            emgServiceList.add(emgServiceService.findById(Long.parseLong(data.get("id"))));
            logger.info("No more data (EmgService)");
            return ResponseEntity.ok(emgServiceList);
        }

        logger.info("Get Emg Service Bad request");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/EmergencyServices")
    public ResponseEntity<EmgService> addEmgService(@Valid @RequestBody EmgServiceDTO emgServiceDTO) {
        logger.info("POST EmgService");
        EmgService emgService = emgServiceService.addEmgService(emgServiceDTO);
        logger.info("POST EmgService END");
        return ResponseEntity.status(HttpStatus.OK).body(emgService);
    }

    @PutMapping("/EmergencyServices/{id}")
    public ResponseEntity<EmgService> modifyEmgService(@PathVariable long id, @RequestBody EmgServiceDTO emgServiceDTO) throws EmgServiceNotFoundException {
        logger.info("PUT EmgService");
        EmgService emgService = emgServiceService.modifyEmgService(id, emgServiceDTO);
        logger.info("PUT EmgService END");
        return ResponseEntity.status(HttpStatus.OK).body(emgService);
    }

    @DeleteMapping("EmergencyServices/{id}")
    public ResponseEntity<Void> deleteEmgService(@PathVariable long id) throws EmgServiceNotFoundException {
        logger.info("DELETE EmgService");
        emgServiceService.deleteEmgService(id);
        logger.info("DELETE EmgService END");
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(EmgServiceNotFoundException.class)
    public ResponseEntity<ErrorException> handleEmgServiceNotFoundException(EmgServiceNotFoundException enf) {
        ErrorException errorException = new ErrorException(404, enf.getMessage());
        logger.info("Emg Service not found");

        return new ResponseEntity<>(errorException, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorException> handleException(Exception e) {
        ErrorException error = new ErrorException(500, "Oops, something went wrong");
        logger.info("ERROREXCEPTION:" + e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
