package com.pisoft.pisoft.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String name;
    private String specialization;

    @Email
    private String email;
    @CreationTimestamp
    private LocalDateTime createAt;

    @OneToMany(mappedBy = "doctor")
    @ToString.Exclude
    private Set<Appointment> appointment = new HashSet<>();

    @ManyToMany
            @JoinTable(
                    name = "doctor_department", // this is the table name
                    joinColumns = @JoinColumn(name = "doctor_id"),
                    inverseJoinColumns = @JoinColumn(name = "department_id")
            )
    @ToString.Exclude
    Set<Department> departments = new HashSet<>();

}
