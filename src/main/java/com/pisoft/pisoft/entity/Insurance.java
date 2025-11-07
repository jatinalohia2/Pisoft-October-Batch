package com.pisoft.pisoft.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String policyNumber;
    private String provider;
    private LocalDate validUntil;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "insurance")
    private Patient patient;


}
