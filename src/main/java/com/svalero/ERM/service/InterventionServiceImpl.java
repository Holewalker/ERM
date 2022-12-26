package com.svalero.ERM.service;

import com.svalero.ERM.domain.EmgService;
import com.svalero.ERM.domain.Incident;
import com.svalero.ERM.domain.Intervention;
import com.svalero.ERM.domain.Report;
import com.svalero.ERM.domain.dto.InterventionDTO;
import com.svalero.ERM.exception.EmgServiceNotFoundException;
import com.svalero.ERM.exception.IncidentNotFoundException;
import com.svalero.ERM.exception.InterventionNotFoundException;
import com.svalero.ERM.repository.EmgServiceRepository;
import com.svalero.ERM.repository.IncidentRepository;
import com.svalero.ERM.repository.InterventionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterventionServiceImpl implements InterventionService {

    @Autowired
    private InterventionRepository interventionRepository;

    private final Logger logger = LoggerFactory.getLogger(InterventionServiceImpl.class);
    @Autowired
    private IncidentRepository incidentRepository;
    @Autowired
    private EmgServiceRepository emgServiceRepository;

    @Override
    public List<Intervention> findAll() {
        return interventionRepository.findAll();
    }

    @Override
    public Intervention findById(long id) throws InterventionNotFoundException {
        logger.info("ID Intervention: " + id);
        return interventionRepository.findById(id).orElseThrow(InterventionNotFoundException::new);
    }


    @Override
    public List<Intervention> findByStatus(int status) {
        logger.info("Intervention Status: " + status);
        return interventionRepository.findByStatus(status);
    }


    @Override
    public Intervention addIntervention(InterventionDTO interventionDTO) throws IncidentNotFoundException, EmgServiceNotFoundException {
        logger.info("Creating Intervention " + interventionDTO);
        Intervention newIntervention = new Intervention();
        Incident incident = incidentRepository.findById(interventionDTO.getIncidentId()).orElseThrow(IncidentNotFoundException::new);
        newIntervention.setIncidentIntervention(incident);
        EmgService emgService = emgServiceRepository.findById(interventionDTO.getEmergencyServiceId()).orElseThrow(EmgServiceNotFoundException::new);
        newIntervention.setEmgServiceIntervention(emgService);
        newIntervention.setStatus(interventionDTO.getStatus());
        newIntervention.setDispatchdate(interventionDTO.getDispatchDate());
        newIntervention.setEndDate(interventionDTO.getEndDate());
        return interventionRepository.save(newIntervention);
    }

    @Override
    public void deleteIntervention(long id) throws InterventionNotFoundException {
        logger.info("deleting Intervention " + id);
        Intervention intervention = interventionRepository.findById(id).orElseThrow(InterventionNotFoundException::new);
        logger.info(" " + intervention);
        interventionRepository.delete(intervention);
    }

    @Override
    public Intervention modifyIntervention(long id, InterventionDTO interventionDTO) throws InterventionNotFoundException, IncidentNotFoundException, EmgServiceNotFoundException {
        Intervention currentIntervention = interventionRepository.findById(id).orElseThrow(InterventionNotFoundException::new);

        logger.info("Changing Intervention " + id + currentIntervention);
        Incident currentincident = incidentRepository.findById(interventionDTO.getIncidentId()).orElseThrow(IncidentNotFoundException::new);
        currentIntervention.setIncidentIntervention(currentincident);
        EmgService currentEmgService = emgServiceRepository.findById(interventionDTO.getEmergencyServiceId()).orElseThrow(EmgServiceNotFoundException::new);
        currentIntervention.setEmgServiceIntervention(currentEmgService);
        currentIntervention.setStatus(interventionDTO.getStatus());
        currentIntervention.setDispatchdate(interventionDTO.getDispatchDate());
        currentIntervention.setEndDate(interventionDTO.getEndDate());
        logger.info("Intervention Changed " + id + interventionDTO);
        return interventionRepository.save(currentIntervention);
    }


}
