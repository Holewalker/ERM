package com.svalero.ERM.service;

import com.svalero.ERM.domain.EmgVehicle;
import com.svalero.ERM.domain.dto.EmgVehicleDTO;
import com.svalero.ERM.exception.EmgVehicleNotFoundException;
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
    public EmgVehicle addEmgVehicle(EmgVehicleDTO emgVehicleDTO) {
        logger.info("Creating Vehicle " + emgVehicleDTO);
        EmgVehicle newEmgVehicle = new EmgVehicle();
        newEmgVehicle.setModel(emgVehicleDTO.getModel());
        newEmgVehicle.setVin(emgVehicleDTO.getVin());
        newEmgVehicle.setType(emgVehicleDTO.getType());
        newEmgVehicle.setType(emgVehicleDTO.getType());
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
    public EmgVehicle modifyEmgVehicle(long id, EmgVehicleDTO emgVehicleDTO) throws EmgVehicleNotFoundException {
        EmgVehicle currentEmgVehicle = emgVehicleRepository.findById(id).orElseThrow(EmgVehicleNotFoundException::new);
        logger.info("Changing Vehicle " + id + currentEmgVehicle);
        currentEmgVehicle.setModel(emgVehicleDTO.getModel());
        currentEmgVehicle.setVin(emgVehicleDTO.getVin());
        currentEmgVehicle.setType(emgVehicleDTO.getType());
        currentEmgVehicle.setType(emgVehicleDTO.getType());
        logger.info("Vehicle Changed " + id + emgVehicleDTO);
        return emgVehicleRepository.save(currentEmgVehicle);
    }


}
