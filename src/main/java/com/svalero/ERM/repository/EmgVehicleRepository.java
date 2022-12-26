package com.svalero.ERM.repository;

import com.svalero.ERM.domain.EmgVehicle;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmgVehicleRepository extends CrudRepository<EmgVehicle, Long> {
    List<EmgVehicle> findAll();
    List<EmgVehicle> findByTypeContainingIgnoreCase(String type);
    List<EmgVehicle> findByModelContainingIgnoreCase(String model);

}
