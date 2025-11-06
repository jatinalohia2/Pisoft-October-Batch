package com.pisoft.pisoft.service;

import com.pisoft.pisoft.dto.StudentDTO;
import com.pisoft.pisoft.entity.Student;
import com.pisoft.pisoft.exception.ResourceNotFound;
import com.pisoft.pisoft.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public List<StudentDTO> findAll() {
        return studentRepository.findAll()
                .stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .toList();
    }

    public StudentDTO save(Student student) {
        Student savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent, StudentDTO.class);
    }

    public StudentDTO findByStudentId(Integer id) {
        Student student = getStudentOrThrow(id);
        Student student2 = getStudentOrThrow(id);

        System.out.println(student == student2);


        return modelMapper.map(student, StudentDTO.class);
    }

    public void deleteStudentById(Integer studentId) {
        Student student = getStudentOrThrow(studentId);
        studentRepository.delete(student);
    }

    public StudentDTO updateWholeStudent(Integer studentId, Student student) {
        getStudentOrThrow(studentId); // ensure it exists
        student.setId(studentId);
        Student updatedStudent = studentRepository.save(student);
        return modelMapper.map(updatedStudent, StudentDTO.class);
    }

    private Student getStudentOrThrow(Integer id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Student not found with id: " + id));
    }
}
