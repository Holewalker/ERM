package com.svalero.ERM.controller;

import com.svalero.ERM.domain.EmgService;
import com.svalero.ERM.domain.dto.EmgServiceDTO;
import com.svalero.ERM.exception.EmgServiceNotFoundException;
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
public class EmgServiceController {

    @Autowired
    EmgServiceService emgServiceService;


    @GetMapping("/EmergencyServices")
    public ResponseEntity<List<EmgService>> getEmgServices(@RequestParam Map<String, String> data) throws EmgServiceNotFoundException {

        if (data.isEmpty()) {
            return ResponseEntity.ok(emgServiceService.findAll());
        } else if (data.containsKey("location")) {
            List<EmgService> emgServiceList = emgServiceService.findByLocation(data.get("location"));
            return ResponseEntity.ok(emgServiceList);
        } else if (data.containsKey("id")) {
            List<EmgService> emgServiceList = new ArrayList<>();
            emgServiceList.add(emgServiceService.findById(Long.parseLong(data.get("id"))));
            return ResponseEntity.ok(emgServiceList);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/EmergencyServices")
    public ResponseEntity<EmgService> addEmgService(@Valid @RequestBody EmgServiceDTO emgServiceDTO) {

        EmgService emgService = emgServiceService.addEmgService(emgServiceDTO);

        return ResponseEntity.status(HttpStatus.OK).body(emgService);
    }

    @PutMapping("/EmergencyServices/{id}")
    public ResponseEntity<EmgService> modifyEmgService(@PathVariable long id, @RequestBody EmgServiceDTO emgServiceDTO) throws EmgServiceNotFoundException {
        EmgService emgService = emgServiceService.modifyEmgService(id, emgServiceDTO);
        return ResponseEntity.status(HttpStatus.OK).body(emgService);
    }

    @DeleteMapping("inventories/{id}")
    public ResponseEntity<Void> deleteEmgService(@PathVariable long id) throws EmgServiceNotFoundException {
        emgServiceService.deleteEmgService(id);
        return ResponseEntity.noContent().build();
    }
}
