package com.pisoft.pisoft.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    private String name;
    private String rollNo;
    private LocalDate localDate;
    private Boolean active;
}
