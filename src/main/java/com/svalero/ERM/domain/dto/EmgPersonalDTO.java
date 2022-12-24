package com.svalero.ERM.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmgPersonalDTO {

    String fullName;
    String idNumber;
    String type;
    String title;

}
