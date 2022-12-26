package com.svalero.ERM.controller;

import com.svalero.ERM.domain.EmgVehicle;
import com.svalero.ERM.domain.dto.EmgVehicleDTO;
import com.svalero.ERM.exception.EmgVehicleNotFoundException;
import com.svalero.ERM.exception.ErrorException;
import com.svalero.ERM.service.EmgVehicleService;
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
public class EmgVehicleController {

    @Autowired
    EmgVehicleService emgVehicleService;

    private final Logger logger = LoggerFactory.getLogger(EmgVehicleController.class);

    @GetMapping("/EmergencyVehicles")
    public ResponseEntity<List<EmgVehicle>> getEmgVehicles(@RequestParam Map<String, String> data) throws EmgVehicleNotFoundException {
        logger.info("GET EmgVehicle");

        if (data.isEmpty()) {
            logger.info("No more data (EmgVehicle)");
            return ResponseEntity.ok(emgVehicleService.findAll());
        } else if (data.containsKey("model")) {
            logger.info("model:" + data.get("model"));
            List<EmgVehicle> emgVehicleList = emgVehicleService.findByModel(data.get("model"));
            logger.info("No more data (EmgVehicle)");
            return ResponseEntity.ok(emgVehicleList);
        } else if (data.containsKey("type")) {
            logger.info("type:" + data.get("type"));
            List<EmgVehicle> emgVehicleList = emgVehicleService.findByType(data.get("type"));
            logger.info("No more data (EmgVehicle)");
            return ResponseEntity.ok(emgVehicleList);
        } else if (data.containsKey("id")) {
            logger.info("id:" + data.get("id"));
            List<EmgVehicle> emgVehicleList = new ArrayList<>();
            emgVehicleList.add(emgVehicleService.findById(Long.parseLong(data.get("id"))));
            logger.info("No more data (EmgVehicle)");
            return ResponseEntity.ok(emgVehicleList);
        }

        logger.info("Get EmgVehicle Bad request");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/EmergencyVehicles")
    public ResponseEntity<EmgVehicle> addEmgVehicle(@Valid @RequestBody EmgVehicleDTO emgVehicleDTO) {
        logger.info("POST EmgVehicle");
        EmgVehicle emgVehicle = emgVehicleService.addEmgVehicle(emgVehicleDTO);
        logger.info("POST EmgVehicle END");
        return ResponseEntity.status(HttpStatus.OK).body(emgVehicle);
    }

    @PutMapping("/EmergencyVehicles/{id}")
    public ResponseEntity<EmgVehicle> modifyEmgVehicle(@PathVariable long id, @RequestBody EmgVehicleDTO emgVehicleDTO) throws EmgVehicleNotFoundException {
        logger.info("PUT EmgVehicle");
        EmgVehicle emgVehicle = emgVehicleService.modifyEmgVehicle(id, emgVehicleDTO);
        logger.info("PUT EmgVehicle END");
        return ResponseEntity.status(HttpStatus.OK).body(emgVehicle);
    }

    @DeleteMapping("EmergencyVehicles/{id}")
    public ResponseEntity<Void> deleteEmgVehicle(@PathVariable long id) throws EmgVehicleNotFoundException {
        logger.info("DELETE EmgVehicle");
        emgVehicleService.deleteEmgVehicle(id);
        logger.info("DELETE EmgVehicle END");
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(EmgVehicleNotFoundException.class)
    public ResponseEntity<ErrorException> handleEmgVehicleNotFoundException(EmgVehicleNotFoundException enf) {
        ErrorException errorException = new ErrorException(404, enf.getMessage());
        logger.info("EmgVehicle not found");

        return new ResponseEntity<>(errorException, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorException> handleException(Exception e) {
        ErrorException error = new ErrorException(500, "Oops, something went wrong");
        logger.info("ERROREXCEPTION:" + e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
