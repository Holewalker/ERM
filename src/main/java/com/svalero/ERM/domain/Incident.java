package com.svalero.ERM.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Incident")
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /*
     * Location
     * Lat
     * Long
     * Status
     * Description
     * Start Date
     * */

    @Column
    @NotNull(message = "Location must be specified")
    @NotBlank(message = "Location must be specified")
    private String location;

    @Column
    @NotNull(message = "Coordinates must be specified")
    private double lat;

    @Column
    @NotNull(message = "Coordinates must be specified")
    private double lon;

    @Column
    @Range(min = 0, max = 100, message = "status must be between 0% and 100%")
    private int status;

    @Column
    private String description;

    @Column
    private LocalDate startTime;
}
