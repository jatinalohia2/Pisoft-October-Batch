package com.pisoft.pisoft.service;

import com.pisoft.pisoft.entity.Insurance;
import com.pisoft.pisoft.entity.Patient;
import com.pisoft.pisoft.repository.InsuranceRepository;
import com.pisoft.pisoft.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final PatientRepository patientRepository;
    private final InsuranceRepository insuranceRepository;

    private final static Logger logger = LoggerFactory.getLogger(InsuranceService.class);

    public void hello() {
        logger.trace("This is a TRACE message");
        logger.debug("This is a DEBUG message");
        logger.info("This is an INFO message");
        logger.warn("This is a WARN message");
        logger.error("This is an ERROR message");
    }

    @Transactional
    public Patient assignInsuranceToPatient(Insurance insurance, long patientId) {

        logger.info("Assign insurance to patient insurance ="+insurance + " patientId = "+patientId );
        logger.info("Assign insurance to patient insurance = {} amd patientId = {} and jatin = {}" , insurance , patientId ,"jatoin");

        Patient patient = patientRepository.findById(patientId).orElseThrow();

        patient.setInsurance(insurance); // this part of setting insurance to patient :
        // save insurance (cascade type all )

        patientRepository.save(patient); //
        return patient;
    }

    @Transactional
    public Patient updateInsuranceToPatient(Insurance insurance, long patientId) {

        Patient patient = patientRepository.findById(patientId).orElseThrow();
        patient.setInsurance(insurance);
        return patient;
    }

    @Transactional
    public Patient removeInsuranceToPatient(long patientId) {

        Patient patient = patientRepository.findById(patientId).orElseThrow();
        patient.setInsurance(null);
        return patient;
    }
}


