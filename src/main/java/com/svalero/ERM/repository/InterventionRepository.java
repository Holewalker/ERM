package com.svalero.ERM.repository;

import com.svalero.ERM.domain.EmgVehicle;
import com.svalero.ERM.domain.Intervention;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InterventionRepository extends CrudRepository<Intervention, Long> {
    List<Intervention> findAll();
    //TODO find by emgService ID
    List<Intervention> findByStatus(int status);

}
