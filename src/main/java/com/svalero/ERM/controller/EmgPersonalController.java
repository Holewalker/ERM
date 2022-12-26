package com.svalero.ERM.controller;

import com.svalero.ERM.domain.EmgPersonal;
import com.svalero.ERM.domain.EmgService;
import com.svalero.ERM.domain.dto.EmgPersonalDTO;
import com.svalero.ERM.domain.dto.EmgServiceDTO;
import com.svalero.ERM.exception.EmgPersonalNotFoundException;
import com.svalero.ERM.exception.EmgServiceNotFoundException;
import com.svalero.ERM.exception.ErrorException;
import com.svalero.ERM.service.EmgPersonalService;
import com.svalero.ERM.service.EmgServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class EmgPersonalController {

    @Autowired
    EmgPersonalService emgPersonalService;


    @GetMapping("/EmergencyPersonal")
    public ResponseEntity<List<EmgPersonal>> getEmgPersonal(@RequestParam Map<String, String> data) throws EmgPersonalNotFoundException {

        if (data.isEmpty()) {
            return ResponseEntity.ok(emgPersonalService.findAll());
        } else if (data.containsKey("location")) {
            List<EmgPersonal> emgPersonalList = emgPersonalService.findByType(data.get("Type"));
            return ResponseEntity.ok(emgPersonalList);
        } else if (data.containsKey("id")) {
            List<EmgPersonal> emgPersonalList = new ArrayList<>();
            emgPersonalList.add(emgPersonalService.findById(Long.parseLong(data.get("id"))));
            return ResponseEntity.ok(emgPersonalList);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/EmergencyPersonal")
    public ResponseEntity<EmgPersonal> addEmgPersonal(@Valid @RequestBody EmgPersonalDTO emgPersonalDTO) {

        EmgPersonal emgPersonal = emgPersonalService.addEmgPersonal(emgPersonalDTO);

        return ResponseEntity.status(HttpStatus.OK).body(emgPersonal);
    }

    @PutMapping("/EmergencyPersonal/{id}")
    public ResponseEntity<EmgPersonal> modifyEmgPersonal(@PathVariable long id, @RequestBody EmgPersonalDTO emgPersonalDTO) throws EmgPersonalNotFoundException {
        EmgPersonal emgPersonal = emgPersonalService.modifyEmgPersonal(id, emgPersonalDTO);
        return ResponseEntity.status(HttpStatus.OK).body(emgPersonal);
    }

    @DeleteMapping("EmergencyPersonal/{id}")
    public ResponseEntity<Void> deleteEmgPersonal(@PathVariable long id) throws EmgPersonalNotFoundException {
        emgPersonalService.deleteEmgPersonal(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(EmgPersonalNotFoundException.class)
    public ResponseEntity<ErrorException> handleEmgPersonalNotFoundException(EmgPersonalNotFoundException enf) {
        ErrorException errorException = new ErrorException(404, enf.getMessage());
        return new ResponseEntity<>(errorException, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorException> handleException(Exception e) {
        ErrorException error = new ErrorException(500, "Oops, something went wrong");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
