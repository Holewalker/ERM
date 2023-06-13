package com.svalero.ERM.controller;

import com.svalero.ERM.domain.EmgVehicle;
import com.svalero.ERM.domain.dto.EmgVehicleDTO;
import com.svalero.ERM.exception.EmgServiceNotFoundException;
import com.svalero.ERM.exception.EmgVehicleNotFoundException;
import com.svalero.ERM.exception.FileNotImageException;
import com.svalero.ERM.service.EmgServiceService;
import com.svalero.ERM.util.ErrorUtil;
import com.svalero.ERM.service.EmgVehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.svalero.ERM.util.ErrorUtil.getErrorExceptionResponseEntity;

@RestController
public class EmgVehicleController {

    @Autowired
    EmgVehicleService emgVehicleService;
    @Autowired
    EmgServiceService emgServiceService;

    private final Logger logger = LoggerFactory.getLogger(EmgVehicleController.class);

    @GetMapping("/EmergencyVehicles")
    public ResponseEntity<List<EmgVehicle>> getEmgVehicles(@RequestParam Map<String, String> data) throws EmgVehicleNotFoundException, EmgServiceNotFoundException {
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
        } else if (data.containsKey("emgService")) {
            logger.info("emgService:" + data.get("emgService"));
            List<EmgVehicle> emgVehicleList = emgVehicleService.findByEmgService(Long.parseLong(data.get("emgService")));
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
    public ResponseEntity<EmgVehicle> addEmgVehicle(@Valid @RequestBody EmgVehicleDTO emgVehicleDTO) throws EmgServiceNotFoundException {
        logger.info("POST EmgVehicle");
        EmgVehicle emgVehicle = emgVehicleService.addEmgVehicle(emgVehicleDTO);
        logger.info("POST EmgVehicle END");
        return ResponseEntity.status(HttpStatus.OK).body(emgVehicle);
    }

    @PostMapping("/EmergencyVehicles/{id}/image")
    public ResponseEntity<EmgVehicle> addImageToEmgVehicle(@PathVariable long id, @RequestParam("file") MultipartFile file) throws EmgVehicleNotFoundException, IOException, FileNotImageException {
        logger.info("POST Image");
        EmgVehicle emgVehicle = emgVehicleService.saveImage(id, file);
        logger.info("END POST Image");
        return ResponseEntity.ok(emgVehicle);
    }

    @PutMapping("/EmergencyVehicles/{id}")
    public ResponseEntity<EmgVehicle> modifyEmgVehicle(@PathVariable long id, @Valid @RequestBody EmgVehicleDTO emgVehicleDTO) throws EmgVehicleNotFoundException, EmgServiceNotFoundException {
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorUtil> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        logger.error("Data is not correct");
        return getErrorExceptionResponseEntity(methodArgumentNotValidException);
    }

    @ExceptionHandler(EmgVehicleNotFoundException.class)
    public ResponseEntity<ErrorUtil> handleEmgVehicleNotFoundException(EmgVehicleNotFoundException enf) {
        ErrorUtil errorException = new ErrorUtil(404, enf.getMessage());
        logger.info("EmgVehicle not found");

        return new ResponseEntity<>(errorException, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorUtil> handleException(Exception e) {
        ErrorUtil error = new ErrorUtil(500, "Oops, something went wrong");
        logger.info("ERROREXCEPTION:" + e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
