package com.svalero.ERM.service;

import com.svalero.ERM.domain.EmgService;
import com.svalero.ERM.domain.dto.EmgServiceDTO;
import com.svalero.ERM.exception.EmgServiceNotFoundException;
import com.svalero.ERM.repository.EmgServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmgServiceServiceImpl implements EmgServiceService {

    @Autowired
    private EmgServiceRepository emgServiceRepository;

    private final Logger logger = LoggerFactory.getLogger(EmgPersonalServiceImpl.class);

    @Override
    public List<EmgService> findAll() {
        return emgServiceRepository.findAll();
    }

    @Override
    public EmgService findById(long id) throws EmgServiceNotFoundException {
        logger.info("Service ID: " + id);
        return emgServiceRepository.findById(id).orElseThrow(EmgServiceNotFoundException::new);
    }

    @Override
    public List<EmgService> findByLocation(String location) {
        logger.info("Service location: " + location);
        return emgServiceRepository.findByLocationContainingIgnoreCase(location);
    }

    @Override
    public List<EmgService> findByType(String type) {
        logger.info("Service type: " + type);
        return emgServiceRepository.findByTypeContainingIgnoreCase(type);
    }

    @Override
    public EmgService addEmgService(EmgServiceDTO emgServiceDTO) {

        logger.info("Creating Service " + emgServiceDTO);
        EmgService newEmgService = new EmgService();
        newEmgService.setLocation(emgServiceDTO.getLocation());
        newEmgService.setType(emgServiceDTO.getType());
        return emgServiceRepository.save(newEmgService);
    }

    @Override
    public void deleteEmgService(long id) throws EmgServiceNotFoundException {
        logger.info("deleting Emg Service " + id);
        EmgService emgService = emgServiceRepository.findById(id).orElseThrow(EmgServiceNotFoundException::new);
        logger.info(" " + emgService);
        emgServiceRepository.delete(emgService);
    }

    @Override
    public EmgService modifyEmgService(long id, EmgServiceDTO emgServiceDTO) throws EmgServiceNotFoundException {
        EmgService currentEmgService = emgServiceRepository.findById(id).orElseThrow(EmgServiceNotFoundException::new);
        logger.info("Changing EmgService " + id + currentEmgService);
        currentEmgService.setLocation(emgServiceDTO.getLocation());
        currentEmgService.setType(emgServiceDTO.getType());
        logger.info("EmgService Changed " + id + emgServiceDTO);
        return emgServiceRepository.save(currentEmgService);
    }


}
