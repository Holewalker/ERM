package com.svalero.ERM.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Report")

public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "intervention_id")
    private Intervention interventionReport;

    @Column
    private LocalDate endDate;


    @Column
    @NotNull(message = "Title must be specified")
    @NotBlank(message = "Title must be specified")
    private String title;

    @Column
    @NotNull(message = "Text must be specified")
    @NotBlank(message = "Text must be specified")
    private String text;

    @Column
    private String reporterComment;



    /*
     * Report Date
     * Title
     * Text
     * Reporter comment
     * */
}
