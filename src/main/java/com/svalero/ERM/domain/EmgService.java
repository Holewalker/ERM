package com.svalero.ERM.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "EmgService")
public class EmgService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotNull(message = "Location must be specified")
    @NotBlank(message = "Location must be specified")
    private String location;


    @Column
    @NotNull(message = "Type of service must be specified")
    @NotBlank(message = "Type of service must be specified")
    private String type;


}
