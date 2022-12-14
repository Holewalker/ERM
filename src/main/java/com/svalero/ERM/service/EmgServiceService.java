package com.svalero.ERM.service;

import com.svalero.ERM.domain.EmgService;
import com.svalero.ERM.domain.dto.EmgServiceDTO;
import com.svalero.ERM.exception.EmgServiceNotFoundException;

import java.util.List;

public interface EmgServiceService {
    List<EmgService> findAll();

    EmgService findById(long id) throws EmgServiceNotFoundException;

    List<EmgService> findByLocation(String location);

    EmgService addEmgService(EmgServiceDTO emgServiceDTO);

    void deleteEmgService(long id) throws EmgServiceNotFoundException;

    EmgService modifyEmgService(long id, EmgServiceDTO EmgServiceDTO) throws EmgServiceNotFoundException;
}
