package com.svalero.ERM.service;

import com.svalero.ERM.domain.EmgVehicle;
import com.svalero.ERM.domain.Incident;
import com.svalero.ERM.domain.dto.EmgVehicleDTO;
import com.svalero.ERM.domain.dto.IncidentDTO;
import com.svalero.ERM.exception.EmgVehicleNotFoundException;
import com.svalero.ERM.exception.IncidentNotFoundException;

import java.util.List;

public interface IncidentService {

    List<Incident> findAll();

    Incident findById(long id) throws IncidentNotFoundException;

    List<Incident> findByLocation(String location);

    List<Incident> findByStatus(int status);

    Incident addIncident(IncidentDTO incidentDTO);

    void deleteIncident(long id) throws IncidentNotFoundException;

    Incident modifyIncident(long id, IncidentDTO incidentDTO) throws IncidentNotFoundException;
}
