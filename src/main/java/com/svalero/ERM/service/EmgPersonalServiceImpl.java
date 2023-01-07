package com.svalero.ERM.service;

import com.svalero.ERM.domain.EmgPersonal;
import com.svalero.ERM.domain.EmgService;
import com.svalero.ERM.domain.dto.EmgPersonalDTO;
import com.svalero.ERM.exception.EmgPersonalNotFoundException;
import com.svalero.ERM.exception.EmgServiceNotFoundException;
import com.svalero.ERM.repository.EmgPersonalRepository;
import com.svalero.ERM.repository.EmgServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmgPersonalServiceImpl implements EmgPersonalService {

    @Autowired
    private EmgPersonalRepository emgPersonalRepository;
    @Autowired
    private EmgServiceRepository emgServiceRepository;
    private final Logger logger = LoggerFactory.getLogger(EmgPersonalServiceImpl.class);
    @Override
    public List<EmgPersonal> findAll() {
        return emgPersonalRepository.findAll();
    }

    @Override
    public EmgPersonal findById(long id) throws EmgPersonalNotFoundException {
        logger.info("ID Personal: " + id);
        return emgPersonalRepository.findById(id).orElseThrow(EmgPersonalNotFoundException::new);
    }

    @Override
    public List<EmgPersonal> findByType(String type) {
        logger.info("Personal type: " + type);
        return emgPersonalRepository.findByTypeContainingIgnoreCase(type);
    }

    @Override
    public List<EmgPersonal> findByFullName(String fullName) {
        logger.info("Personal fullName: " + fullName);
        return emgPersonalRepository.findByFullNameContainingIgnoreCase(fullName);
    }

    @Override
    public List<EmgPersonal> findByIdNumber(String idNumber) {
        logger.info("Personal idNumber: " + idNumber);
        return emgPersonalRepository.findByIdNumberContainingIgnoreCase(idNumber);
    }

    @Override
    public EmgPersonal addEmgPersonal(EmgPersonalDTO emgPersonalDTO) throws EmgServiceNotFoundException {
        logger.info("Creating Personal " + emgPersonalDTO);
        EmgPersonal newEmgPersonal = new EmgPersonal();
        EmgService emgService  = emgServiceRepository.findById(emgPersonalDTO.getEmgServiceId()).orElseThrow(EmgServiceNotFoundException::new);
        newEmgPersonal.setEmgServicePersonal(emgService);
        newEmgPersonal.setFullName(emgPersonalDTO.getFullName());
        newEmgPersonal.setIdNumber(emgPersonalDTO.getIdNumber());
        newEmgPersonal.setType(emgPersonalDTO.getType());
        newEmgPersonal.setTitle(emgPersonalDTO.getTitle());
        return emgPersonalRepository.save(newEmgPersonal);
    }

    @Override
    public void deleteEmgPersonal(long id) throws EmgPersonalNotFoundException {
        logger.info("deleting Personal " + id);
        EmgPersonal emgPersonal = emgPersonalRepository.findById(id).orElseThrow(EmgPersonalNotFoundException::new);
        logger.info(" " + emgPersonal);
        emgPersonalRepository.delete(emgPersonal);
    }

    @Override
    public EmgPersonal modifyEmgPersonal(long id, EmgPersonalDTO emgPersonalDTO) throws EmgPersonalNotFoundException, EmgServiceNotFoundException {
        EmgPersonal currentEmgPersonal = emgPersonalRepository.findById(id).orElseThrow(EmgPersonalNotFoundException::new);
        logger.info("Changing Personal " + id + currentEmgPersonal);
        EmgService currentEmgService  = emgServiceRepository.findById(emgPersonalDTO.getEmgServiceId()).orElseThrow(EmgServiceNotFoundException::new);
        currentEmgPersonal.setEmgServicePersonal(currentEmgService);
        currentEmgPersonal.setFullName(emgPersonalDTO.getFullName());
        currentEmgPersonal.setIdNumber(emgPersonalDTO.getIdNumber());
        currentEmgPersonal.setType(emgPersonalDTO.getType());
        currentEmgPersonal.setTitle(emgPersonalDTO.getTitle());
        logger.info("Personal Changed " + id + emgPersonalDTO);
        return emgPersonalRepository.save(currentEmgPersonal);
    }


}
