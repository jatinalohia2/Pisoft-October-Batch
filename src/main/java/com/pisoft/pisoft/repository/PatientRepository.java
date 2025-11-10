package com.pisoft.pisoft.repository;

import com.pisoft.pisoft.entity.Customer;
import com.pisoft.pisoft.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("select p from Patient p Left join FETCH p.appointment")
    List<Patient> findAllPatientsAppointments();


}
