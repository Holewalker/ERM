package com.svalero.ERM.service;

import com.svalero.ERM.domain.EmgPersonal;
import com.svalero.ERM.domain.EmgService;
import com.svalero.ERM.domain.dto.EmgPersonalDTO;
import com.svalero.ERM.domain.dto.EmgServiceDTO;
import com.svalero.ERM.exception.EmgPersonalNotFoundException;
import com.svalero.ERM.exception.EmgServiceNotFoundException;
import com.svalero.ERM.repository.EmgPersonalRepository;
import com.svalero.ERM.repository.EmgServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class emgPersonalServiceImpl implements EmgPersonalService {

    @Autowired
    private EmgPersonalRepository emgPersonalRepository;

    @Override
    public List<EmgPersonal> findAll() {
        return emgPersonalRepository.findAll();
    }

    @Override
    public EmgPersonal findById(long id) throws EmgPersonalNotFoundException {
        return emgPersonalRepository.findById(id).orElseThrow(EmgPersonalNotFoundException::new);
    }

    @Override
    public List<EmgPersonal> findByType(String type) {
        return emgPersonalRepository.findByTypeContainingIgnoreCase(type);
    }

    @Override
    public EmgPersonal addEmgPersonal(EmgPersonalDTO emgPersonalDTO) {
        EmgPersonal newEmgPersonal = new EmgPersonal();
        newEmgPersonal.setFullName(emgPersonalDTO.getFullName());
        newEmgPersonal.setIdNumber(emgPersonalDTO.getIdNumber());
        newEmgPersonal.setType(emgPersonalDTO.getType());
        newEmgPersonal.setTitle(emgPersonalDTO.getTitle());
        return emgPersonalRepository.save(newEmgPersonal);
    }

    @Override
    public void deleteEmgPersonal(long id) throws EmgPersonalNotFoundException {
        EmgPersonal emgPersonal = emgPersonalRepository.findById(id).orElseThrow(EmgPersonalNotFoundException::new);
        emgPersonalRepository.delete(emgPersonal);
    }

    @Override
    public EmgPersonal modifyEmgPersonal(long id, EmgPersonalDTO emgPersonalDTO) throws EmgPersonalNotFoundException {
        EmgPersonal currentEmgPersonal = emgPersonalRepository.findById(id).orElseThrow(EmgPersonalNotFoundException::new);
        currentEmgPersonal.setFullName(emgPersonalDTO.getFullName());
        currentEmgPersonal.setIdNumber(emgPersonalDTO.getIdNumber());
        currentEmgPersonal.setType(emgPersonalDTO.getType());
        currentEmgPersonal.setTitle(emgPersonalDTO.getTitle());
        return emgPersonalRepository.save(currentEmgPersonal);
    }


}
