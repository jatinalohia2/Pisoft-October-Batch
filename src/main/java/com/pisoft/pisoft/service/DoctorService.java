package com.pisoft.pisoft.service;

import com.pisoft.pisoft.entity.Department;
import com.pisoft.pisoft.entity.Doctor;
import com.pisoft.pisoft.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private  final DoctorRepository doctorRepository;

    @Transactional
    public Doctor assignDepartmentsToDoctor(long doctorId, Set<Department> department) {

        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        doctor.setDepartments(department); // emtry 3rd table : // dirty checking
        return doctor;

    }
}
