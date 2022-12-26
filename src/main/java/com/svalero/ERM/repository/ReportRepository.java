package com.svalero.ERM.repository;

import com.svalero.ERM.domain.EmgVehicle;
import com.svalero.ERM.domain.Report;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReportRepository extends CrudRepository<Report, Long> {
    List<Report> findAll();
    List<Report> findByTitleContainingIgnoreCase(String Title);
    List<Report> findByEndDateContainingIgnoreCase(LocalDate endDate);

}
