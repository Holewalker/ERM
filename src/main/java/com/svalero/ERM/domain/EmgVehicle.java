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
@Entity(name = "EmgVehicle")
public class EmgVehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @ManyToOne
    @JoinColumn(name = "emgService_id")
    private EmgService emgServiceVehicle;


    @Column
    @NotNull(message = "Model must be specified")
    @NotBlank(message = "Model must be specified")
    private String model;

    @Column
    @NotNull(message = "Identification method (VIN) must be provided")
    @NotBlank(message = "Identification method (VIN) must be provided")
    //TODO CHECK HOW LONG VINs ARE
    private String vin;

    @Column
    @NotNull(message = "Type of service must be specified")
    @NotBlank(message = "Type of service must be specified")
    private String type;


    @Column
    @Range(min = 0, max = 100, message = "Operating status must be between 0% and 100%")
    private int operStatus;

    @Column
    //initial value always false. Vehicles are not created on route.
    private boolean onRoute = false;

    @Column
    private boolean available = true;

    @Column
    @NotNull(message = "Location must be specified")
    @NotBlank(message = "Location must be specified")
    private String location;


    @Column
    @NotNull(message = "Coordinates must be specified")
    @NotBlank(message = "Coordinates must be specified")
    private double lat;

    @Column
    @NotNull(message = "Coordinates must be specified")
    @NotBlank(message = "Coordinates must be specified")
    private double lon;

    @Column
    private LocalDate lastMaintenance;


}
