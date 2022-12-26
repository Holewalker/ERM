package com.svalero.ERM.repository;

import com.svalero.ERM.domain.EmgVehicle;
import com.svalero.ERM.domain.Incident;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IncidentRepository extends CrudRepository<Incident, Long> {
    List<Incident> findAll();
    List<Incident> findByLocationContainingIgnoreCase(String location);
    List<Incident> findByStatusContainingIgnoreCase(String status);

}
