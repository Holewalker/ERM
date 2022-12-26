package com.svalero.ERM.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Intervention")
public class Intervention {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @ManyToOne
    @JoinColumn(name = "incident_id")
    private Incident incidentIntervention;


    @OneToOne
    @JoinColumn(name = "emgService_id")
    private EmgService emgServiceIntervention;

    @Column
    @Range(min = 0, max = 100, message = "status must be between 0% and 100%")
    private int status;

    @Column
    private LocalDate dispatchdate;


    @Column
    private LocalDate endDate;

    /*
     * Status
     * Dispatch Date
     * End date
     *
     *
     * */
}
