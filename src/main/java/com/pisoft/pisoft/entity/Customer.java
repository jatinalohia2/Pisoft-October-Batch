package com.pisoft.pisoft.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter
@Setter
public class Customer {

    @Id // primary key :
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment :
    private Integer id;

    private String name;
    private String city;

}
