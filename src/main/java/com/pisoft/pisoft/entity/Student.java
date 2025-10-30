package com.pisoft.pisoft.entity;

import com.pisoft.pisoft.annotion.RoleValidation;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name can not be empty")
    @Size(min = 2 , max = 5 )
    private String name;
    private String rollNo;

    @Digits(integer = 3 , fraction = 2 )
    private BigDecimal salary;

    @Past
    private LocalDate localDate;
    @AssertTrue
    private Boolean active;
    private String password;
    @Email
    private String email;

//    @Pattern(regexp = "^(ADMIN|USER$")
    @RoleValidation
    private String roles;
}