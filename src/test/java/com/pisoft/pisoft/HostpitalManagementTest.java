package com.pisoft.pisoft;

import com.pisoft.pisoft.entity.Insurance;
import com.pisoft.pisoft.entity.Patient;
import com.pisoft.pisoft.enums.BloodGroup;
import com.pisoft.pisoft.service.InsuranceService;
import com.pisoft.pisoft.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class HostpitalManagementTest {

    @Autowired
    private PatientService patientService;

    @Autowired
    private InsuranceService insuranceService;

    @Test
    public void savePatient(){

        Patient patient = Patient.builder()
                .insurance(null)
                .birthDate(LocalDate.of(2022, 12, 12))
                .bloodGroup(BloodGroup.A_POSITIVE)
                .name("jatin")
                .email("j@gmail.com")
                .gender("male")
                .build();

        Patient patient1 = patientService.savePatient(patient);
        System.out.println(patient1);

    }

    @Test
    public void testAssignInsuranceToPatient(){

        Insurance insurance = Insurance.builder()
                .validUntil(LocalDate.now())
                .provider("HDFC")
                .policyNumber("ABC123")
                .build();


        insuranceService.assignInsuranceToPatient(insurance , 1L);


    }

}
