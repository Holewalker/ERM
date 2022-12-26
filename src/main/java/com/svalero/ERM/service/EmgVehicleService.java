package com.svalero.ERM.service;

import com.svalero.ERM.domain.EmgVehicle;
import com.svalero.ERM.domain.dto.EmgVehicleDTO;
import com.svalero.ERM.exception.EmgVehicleNotFoundException;

import java.util.List;

public interface EmgVehicleService {


    List<EmgVehicle> findAll();

    EmgVehicle findById(long id) throws EmgVehicleNotFoundException;

    List<EmgVehicle> findByType(String type);

    List<EmgVehicle> findByModel(String model);

    EmgVehicle addEmgVehicle(EmgVehicleDTO EmgVehicleDTO);

    void deleteEmgVehicle(long id) throws EmgVehicleNotFoundException;

    EmgVehicle modifyEmgVehicle(long id, EmgVehicleDTO EmgVehicleDTO) throws EmgVehicleNotFoundException;

}
