package com.pisoft.pisoft;

import com.pisoft.pisoft.entity.*;
import com.pisoft.pisoft.enums.BloodGroup;
import com.pisoft.pisoft.repository.DoctorRepository;
import com.pisoft.pisoft.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@SpringBootTest
public class HostpitalManagementTest {

    @Autowired
    private PatientService patientService;

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DoctorService doctorService;

    @Test
    public void savePatient(){

        Patient patient = Patient.builder()
                .insurance(null)
                .birthDate(LocalDate.of(2022, 12, 12))
                .bloodGroup(BloodGroup.A_POSITIVE)
                .name("aman")
                .email("a@gmail.com")
                .gender("female")
                .build();

        Patient patient1 = patientService.savePatient(patient);
        System.out.println(patient1);

        Patient patient2 = Patient.builder()
                .insurance(null)
                .birthDate(LocalDate.of(2022, 12, 12))
                .bloodGroup(BloodGroup.A_POSITIVE)
                .name("simran")
                .email("s@gmail.com")
                .gender("female")
                .build();

        Patient patient3 = patientService.savePatient(patient2);
        System.out.println(patient3);

    }

    @Test
    public void saveDoctor(){

        Doctor doctor = Doctor.builder()
                .name("Ram")
                .email("ram@gmail.com")
                .specialization("Ortho..")
                .build();

        Doctor doctor1 = doctorRepository.save(doctor);
        System.out.println(doctor1);

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

    @Test
    public void testBookAppointment(){

        Appointment appointment = Appointment.builder()
                .reason("dummy2")
                .status("not well2")
                .build();

        Appointment bookAppointment = appointmentService.bookAppointment(appointment, 2L, 1L);
        System.out.println(bookAppointment);


        Appointment appointment2 = Appointment.builder()
                .reason("dummy2")
                .status("not well2")
                .build();

        Appointment bookAppointment3 = appointmentService.bookAppointment(appointment2, 3L, 1L);
        System.out.println(bookAppointment3);
    }

    @Test
    public void testAssignDepartmentsToDoctor(){

        Department department1 = Department.builder()
                .name("Ortho")
                .build();

        Department department2 = Department.builder()
                .name("Cardio")
                .build();

        Department department = departmentService.save(department1);
        Department department3 = departmentService.save(department2);

        Doctor doctor = doctorService.assignDepartmentsToDoctor(1L, Set.of(department, department3));
        System.out.println(doctor);

    }


    @Test
    public void testRemoveInsuranceToPAtient(){
        insuranceService.removeInsuranceToPatient(1L);
    }

    // fetch type :

    @Test
    public void getAllPatient(){

        List<Patient> patientList = patientService.findAll();

        for (Patient patient : patientList) {
            System.out.println(patient);
            System.out.println(patient.getAppointment());
        }


//        patientService.rem



    }
}
