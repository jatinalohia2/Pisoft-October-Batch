package com.pisoft.pisoft.service;

import com.pisoft.pisoft.entity.Insurance;
import com.pisoft.pisoft.entity.Patient;
import com.pisoft.pisoft.repository.InsuranceRepository;
import com.pisoft.pisoft.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final PatientRepository patientRepository;
    private final InsuranceRepository insuranceRepository;

    @Transactional
    public Patient assignInsuranceToPatient(Insurance insurance, long patientId) {

        Patient patient = patientRepository.findById(patientId).orElseThrow();

        patient.setInsurance(insurance); // this part of setting insurance to patient :
        // save insurance (cascade type all )

        patientRepository.save(patient); //
        return patient;




    }
}


