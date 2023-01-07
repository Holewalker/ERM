package com.svalero.ERM.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "EmgPersonal")
public class EmgPersonal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "emgService_id")
    private EmgService emgServicePersonal;


    @Column
    @NotNull(message = "Name must be specified")
    @NotBlank(message = "Name must be specified")
    private String fullName;

    @Column
    @NotNull(message = "Identification method must be provided")
    @NotBlank(message = "Identification method must be provided")
    @Length(min=9, max=9, message = "enter a valid ID method")
    private String idNumber;

    @Column
    @NotNull(message = "Type of service must be specified")
    @NotBlank(message = "Type of service must be specified")
    private String type;


    @Column
    private String title;

}
