package com.pisoft.pisoft.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @CreationTimestamp
    private LocalDateTime appointmentTime;
    private String reason;
    private String status;

    @ManyToOne
//    @JoinColumn(name = "app_patient_id") // this will create column name inside your app.table
    @ToString.Exclude
    private Patient patient;

    @ManyToOne
    @ToString.Exclude
    private Doctor doctor;

}
