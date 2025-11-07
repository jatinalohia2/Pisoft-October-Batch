package com.pisoft.pisoft.service;

import com.pisoft.pisoft.entity.Appointment;
import com.pisoft.pisoft.entity.Doctor;
import com.pisoft.pisoft.entity.Patient;
import com.pisoft.pisoft.repository.AppointmentRepository;
import com.pisoft.pisoft.repository.DoctorRepository;
import com.pisoft.pisoft.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;

    @Transactional
    public Appointment bookAppointment(Appointment appointment , Long patientId , Long doctorId){

        Patient patient = patientRepository.findById(patientId).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);

       return appointmentRepository.save(appointment);
    }


}
