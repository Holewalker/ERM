package com.svalero.ERM.repository;

import com.svalero.ERM.domain.EmgService;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmgServiceRepository extends CrudRepository<EmgService, Long> {
    List<EmgService> findAll();

    List<EmgService> findByLocationContainingIgnoreCase(String location);

    List<EmgService> findByTypeContainingIgnoreCase(String type);



}
