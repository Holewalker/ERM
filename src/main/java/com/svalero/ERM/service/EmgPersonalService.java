package com.svalero.ERM.service;

import com.svalero.ERM.domain.EmgPersonal;
import com.svalero.ERM.domain.dto.EmgPersonalDTO;
import com.svalero.ERM.domain.dto.EmgServiceDTO;
import com.svalero.ERM.exception.EmgPersonalNotFoundException;

import java.util.List;

public interface EmgPersonalService {

    List<EmgPersonal> findAll();

    EmgPersonal findById(long id) throws EmgPersonalNotFoundException;

    List<EmgPersonal> findByType(String type);

    List<EmgPersonal>  findByFullName(String fullName);
    List<EmgPersonal>  findByIdNumber(String idNumber);
    EmgPersonal addEmgPersonal(EmgPersonalDTO emgPersonalDTO);

    void deleteEmgPersonal(long id) throws  EmgPersonalNotFoundException;

    EmgPersonal modifyEmgPersonal(long id, EmgPersonalDTO emgPersonalDTO) throws EmgPersonalNotFoundException;


}
