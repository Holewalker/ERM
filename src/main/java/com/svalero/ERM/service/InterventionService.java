package com.svalero.ERM.service;

import com.svalero.ERM.domain.Intervention;
import com.svalero.ERM.domain.dto.InterventionDTO;
import com.svalero.ERM.exception.EmgServiceNotFoundException;
import com.svalero.ERM.exception.IncidentNotFoundException;
import com.svalero.ERM.exception.InterventionNotFoundException;

import java.util.List;

public interface InterventionService {


    List<Intervention> findAll();

    Intervention findById(long id) throws InterventionNotFoundException;

    List<Intervention> findByStatus(String status);

    Intervention addIntervention(InterventionDTO interventionDTO) throws IncidentNotFoundException, EmgServiceNotFoundException;

    void deleteIntervention(long id) throws InterventionNotFoundException;

    Intervention modifyIntervention(long id, InterventionDTO interventionDTO) throws InterventionNotFoundException, IncidentNotFoundException, EmgServiceNotFoundException;
}
