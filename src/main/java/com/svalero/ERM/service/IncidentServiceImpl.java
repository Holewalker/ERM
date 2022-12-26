package com.svalero.ERM.service;

import com.svalero.ERM.domain.Incident;
import com.svalero.ERM.domain.dto.IncidentDTO;
import com.svalero.ERM.exception.EmgVehicleNotFoundException;
import com.svalero.ERM.exception.IncidentNotFoundException;
import com.svalero.ERM.repository.IncidentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidentServiceImpl implements IncidentService {

    @Autowired
    private IncidentRepository incidentRepository;

    private final Logger logger = LoggerFactory.getLogger(IncidentServiceImpl.class);

    @Override
    public List<Incident> findAll() {
        return incidentRepository.findAll();
    }

    @Override
    public Incident findById(long id) throws IncidentNotFoundException {
        logger.info("ID Incident: " + id);
        return incidentRepository.findById(id).orElseThrow(IncidentNotFoundException::new);
    }

    @Override
    public List<Incident> findByLocation(String location) {
        logger.info("Incident Location: " + location);
        return incidentRepository.findByLocationContainingIgnoreCase(location);
    }

    @Override
    public List<Incident> findByStatus(int status) {
        logger.info("Incident Status: " + status);
        return incidentRepository.findByStatus(status);
    }


    @Override
    public Incident addIncident(IncidentDTO incidentDTO) {
        logger.info("Creating Incident " + incidentDTO);
        Incident newIncident = new Incident();
        newIncident.setLocation(incidentDTO.getLocation());
        newIncident.setLat(incidentDTO.getLat());
        newIncident.setLon(incidentDTO.getLon());
        newIncident.setStatus(incidentDTO.getStatus());
        newIncident.setDescription(incidentDTO.getDescription());
        newIncident.setStartTime(incidentDTO.getStartTime());
        return incidentRepository.save(newIncident);
    }

    @Override
    public void deleteIncident(long id) throws IncidentNotFoundException {
        logger.info("deleting Incident " + id);
        Incident incident = incidentRepository.findById(id).orElseThrow(IncidentNotFoundException::new);
        logger.info(" " + incident);
        incidentRepository.delete(incident);
    }

    @Override
    public Incident modifyIncident(long id, IncidentDTO incidentDTO) throws IncidentNotFoundException {
        Incident currentIncident = incidentRepository.findById(id).orElseThrow(IncidentNotFoundException::new);
        logger.info("Changing Incident " + id + currentIncident);
        currentIncident.setLocation(incidentDTO.getLocation());
        currentIncident.setLat(incidentDTO.getLat());
        currentIncident.setLon(incidentDTO.getLon());
        currentIncident.setStatus(incidentDTO.getStatus());
        currentIncident.setDescription(incidentDTO.getDescription());
        currentIncident.setStartTime(incidentDTO.getStartTime());
        logger.info("Incident Changed " + id + incidentDTO);
        return incidentRepository.save(currentIncident);
    }


}
