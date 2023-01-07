package com.svalero.ERM.controller;

import com.svalero.ERM.domain.EmgPersonal;
import com.svalero.ERM.domain.dto.EmgPersonalDTO;
import com.svalero.ERM.exception.EmgPersonalNotFoundException;
import com.svalero.ERM.exception.EmgServiceNotFoundException;
import com.svalero.ERM.util.ErrorUtil;
import com.svalero.ERM.service.EmgPersonalService;
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
public class EmgPersonalController {

    @Autowired
    EmgPersonalService emgPersonalService;

    private final Logger logger = LoggerFactory.getLogger(EmgPersonalController.class);

    @GetMapping("/EmergencyPersonal")
    public ResponseEntity<List<EmgPersonal>> getEmgPersonal(@RequestParam Map<String, String> data) throws EmgPersonalNotFoundException {
        logger.info("GET Emg personal");
        if (data.isEmpty()) {
            logger.info("No more data (Emg Personal)");
            return ResponseEntity.ok(emgPersonalService.findAll());
        } else if (data.containsKey("type")) {
            logger.info("type:" + data.get("type"));
            List<EmgPersonal> emgPersonalList = emgPersonalService.findByType(data.get("type"));
            logger.info("No more data (Emg Personal)");
            return ResponseEntity.ok(emgPersonalList);
        } else if (data.containsKey("fullName")) {
            logger.info("fullName:" + data.get("fullName"));
            List<EmgPersonal> emgPersonalList = emgPersonalService.findByFullName(data.get("fullName"));
            logger.info("No more data (Emg Personal)");
            return ResponseEntity.ok(emgPersonalList);
        } else if (data.containsKey("id")) {
            logger.info("id:" + data.get("id"));
            List<EmgPersonal> emgPersonalList = new ArrayList<>();
            emgPersonalList.add(emgPersonalService.findById(Long.parseLong(data.get("id"))));
            logger.info("No more data (Emg Personal)");
            return ResponseEntity.ok(emgPersonalList);
        }
        logger.info("Get Emg Personal Bad Request");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/EmergencyPersonal")
    public ResponseEntity<EmgPersonal> addEmgPersonal(@Valid @RequestBody EmgPersonalDTO emgPersonalDTO) throws EmgServiceNotFoundException {
        logger.info("POST Emg Personal");
        EmgPersonal emgPersonal = emgPersonalService.addEmgPersonal(emgPersonalDTO);
        logger.info("POST Emg Personal END");
        return ResponseEntity.status(HttpStatus.OK).body(emgPersonal);
    }

    @PutMapping("/EmergencyPersonal/{id}")
    public ResponseEntity<EmgPersonal> modifyEmgPersonal(@PathVariable long id, @Valid @RequestBody EmgPersonalDTO emgPersonalDTO) throws EmgPersonalNotFoundException, EmgServiceNotFoundException {
        logger.info("PUT Emg Personal");
        EmgPersonal emgPersonal = emgPersonalService.modifyEmgPersonal(id, emgPersonalDTO);
        logger.info("PUT Emg Personal END");
        return ResponseEntity.status(HttpStatus.OK).body(emgPersonal);
    }

    @DeleteMapping("EmergencyPersonal/{id}")
    public ResponseEntity<Void> deleteEmgPersonal(@PathVariable long id) throws EmgPersonalNotFoundException {
        logger.info("DELETE Emg Personal");
        emgPersonalService.deleteEmgPersonal(id);
        logger.info("DELETE Emg Personal END");
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorUtil> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        logger.error("Data is not correct");
        return getErrorExceptionResponseEntity(methodArgumentNotValidException);
    }

    @ExceptionHandler(EmgPersonalNotFoundException.class)
    public ResponseEntity<ErrorUtil> handleEmgPersonalNotFoundException(EmgPersonalNotFoundException enf) {
        ErrorUtil errorException = new ErrorUtil(404, enf.getMessage());
        logger.info("Emg Personal not found");
        return new ResponseEntity<>(errorException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorUtil> handleException(Exception e) {
        ErrorUtil error = new ErrorUtil(500, "Oops, something went wrong");
        logger.info("ERROREXCEPTION:" + e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
