package com.pisoft.pisoft.service;

import com.pisoft.pisoft.entity.Department;
import com.pisoft.pisoft.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Transactional
    public Department save(Department department1) {
        return departmentRepository.save(department1);
    }
}
