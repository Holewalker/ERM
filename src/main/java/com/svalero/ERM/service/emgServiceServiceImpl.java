package com.svalero.ERM.service;

import com.svalero.ERM.domain.EmgService;
import com.svalero.ERM.domain.dto.EmgServiceDTO;
import com.svalero.ERM.exception.EmgServiceNotFoundException;
import com.svalero.ERM.repository.EmgServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class emgServiceServiceImpl implements EmgServiceService {

    @Autowired
    private EmgServiceRepository emgServiceRepository;

    @Override
    public List<EmgService> findAll() {
        return emgServiceRepository.findAll();
    }

    @Override
    public EmgService findById(long id) throws EmgServiceNotFoundException {
        return emgServiceRepository.findById(id).orElseThrow(EmgServiceNotFoundException::new);
    }

    @Override
    public List<EmgService> findByLocation(String location) {
        return emgServiceRepository.findByLocationContainingIgnoreCase(location);
    }

    @Override
    public List<EmgService> findByType(String type) {
        return emgServiceRepository.findByTypeContainingIgnoreCase(type);
    }

    @Override
    public EmgService addEmgService(EmgServiceDTO emgServiceDTO) {
        EmgService newEmgService = new EmgService();
        newEmgService.setLocation(emgServiceDTO.getLocation());
        newEmgService.setType(emgServiceDTO.getType());
        return emgServiceRepository.save(newEmgService);
    }

    @Override
    public void deleteEmgService(long id) throws EmgServiceNotFoundException {
        EmgService emgService = emgServiceRepository.findById(id).orElseThrow(EmgServiceNotFoundException::new);
        emgServiceRepository.delete(emgService);
    }

    @Override
    public EmgService modifyEmgService(long id, EmgServiceDTO emgServiceDTO) throws EmgServiceNotFoundException {
        EmgService currentEmgService = emgServiceRepository.findById(id).orElseThrow(EmgServiceNotFoundException::new);
        currentEmgService.setLocation(emgServiceDTO.getLocation());
        currentEmgService.setType(emgServiceDTO.getType());
        return emgServiceRepository.save(currentEmgService);
    }


}
