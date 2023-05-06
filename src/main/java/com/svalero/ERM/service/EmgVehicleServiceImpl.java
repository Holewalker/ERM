package com.svalero.ERM.service;

import com.svalero.ERM.domain.EmgService;
import com.svalero.ERM.domain.EmgVehicle;
import com.svalero.ERM.domain.dto.EmgVehicleDTO;
import com.svalero.ERM.exception.EmgServiceNotFoundException;
import com.svalero.ERM.exception.EmgVehicleNotFoundException;
import com.svalero.ERM.repository.EmgServiceRepository;
import com.svalero.ERM.repository.EmgVehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmgVehicleServiceImpl implements EmgVehicleService {

    @Autowired
    private EmgVehicleRepository emgVehicleRepository;

    private final Logger logger = LoggerFactory.getLogger(EmgVehicleServiceImpl.class);
    @Autowired
    private EmgServiceRepository emgServiceRepository;

    @Override
    public List<EmgVehicle> findAll() {
        return emgVehicleRepository.findAll();
    }

    @Override
    public EmgVehicle findById(long id) throws EmgVehicleNotFoundException {
        logger.info("ID Vehicle: " + id);
        return emgVehicleRepository.findById(id).orElseThrow(EmgVehicleNotFoundException::new);
    }

    @Override
    public List<EmgVehicle> findByType(String type) {
        logger.info("Vehicle type: " + type);
        return emgVehicleRepository.findByTypeContainingIgnoreCase(type);
    }

    @Override
    public List<EmgVehicle> findByModel(String model) {
        logger.info("Vehicle model: " + model);
        return emgVehicleRepository.findByModelContainingIgnoreCase(model);
    }


    @Override
    public EmgVehicle addEmgVehicle(EmgVehicleDTO emgVehicleDTO) throws EmgServiceNotFoundException {
        logger.info("Creating Vehicle " + emgVehicleDTO);
        EmgVehicle newEmgVehicle = new EmgVehicle();
        EmgService emgService = emgServiceRepository.findById(emgVehicleDTO.getEmgServiceId()).orElseThrow(EmgServiceNotFoundException::new);
        newEmgVehicle.setEmgServiceVehicle(emgService);
        newEmgVehicle.setModel(emgVehicleDTO.getModel());
        newEmgVehicle.setVin(emgVehicleDTO.getVin());
        newEmgVehicle.setOperStatus(emgVehicleDTO.getOperStatus());
        newEmgVehicle.setType(emgVehicleDTO.getType());
        newEmgVehicle.setLastMaintenance(emgVehicleDTO.getLastMaintenance());
        newEmgVehicle.setLocation(emgVehicleDTO.getLocation());
        newEmgVehicle.setLat(emgVehicleDTO.getLat());
        newEmgVehicle.setLon(emgVehicleDTO.getLon());
        return emgVehicleRepository.save(newEmgVehicle);
    }

    @Override
    public void deleteEmgVehicle(long id) throws EmgVehicleNotFoundException {
        logger.info("deleting Vehicle " + id);
        EmgVehicle emgVehicle = emgVehicleRepository.findById(id).orElseThrow(EmgVehicleNotFoundException::new);
        logger.info(" " + emgVehicle);
        emgVehicleRepository.delete(emgVehicle);
    }

    @Override
    public EmgVehicle modifyEmgVehicle(long id, EmgVehicleDTO emgVehicleDTO) throws EmgVehicleNotFoundException, EmgServiceNotFoundException {
        EmgVehicle currentEmgVehicle = emgVehicleRepository.findById(id).orElseThrow(EmgVehicleNotFoundException::new);
        logger.info("Changing Vehicle " + id + currentEmgVehicle);
        EmgService currentEmgService = emgServiceRepository.findById(emgVehicleDTO.getEmgServiceId()).orElseThrow(EmgServiceNotFoundException::new);
        currentEmgVehicle.setEmgServiceVehicle(currentEmgService);
        currentEmgVehicle.setModel(emgVehicleDTO.getModel());
        currentEmgVehicle.setVin(emgVehicleDTO.getVin());
        currentEmgVehicle.setOperStatus(emgVehicleDTO.getOperStatus());
        currentEmgVehicle.setType(emgVehicleDTO.getType());
        currentEmgVehicle.setLastMaintenance(emgVehicleDTO.getLastMaintenance());
        currentEmgVehicle.setLocation(emgVehicleDTO.getLocation());
        currentEmgVehicle.setLat(emgVehicleDTO.getLat());
        currentEmgVehicle.setLon(emgVehicleDTO.getLon());
        logger.info("Vehicle Changed " + id + emgVehicleDTO);
        return emgVehicleRepository.save(currentEmgVehicle);
    }

    @Override
    public List<EmgVehicle> findByEmgService(Long emgService) throws EmgServiceNotFoundException {

        logger.info("Vehicle emgService: " + emgService);
        EmgService emgService1 = emgServiceRepository.findById(emgService).orElseThrow(EmgServiceNotFoundException::new);
        return emgVehicleRepository.findByEmgServiceVehicle(emgService1);
    }


}
