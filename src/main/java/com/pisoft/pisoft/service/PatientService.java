package com.pisoft.pisoft.service;

import com.pisoft.pisoft.entity.Patient;
import com.pisoft.pisoft.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    @Transactional
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }
}


