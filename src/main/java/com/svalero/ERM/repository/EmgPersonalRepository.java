package com.svalero.ERM.repository;

import com.svalero.ERM.domain.EmgPersonal;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmgPersonalRepository extends CrudRepository<EmgPersonal, Long> {
    List<EmgPersonal> findAll();

    List<EmgPersonal> findByTypeContainingIgnoreCase(String type);



}
