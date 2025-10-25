package com.pisoft.pisoft.service;

import com.pisoft.pisoft.dto.StudentDTO;
import com.pisoft.pisoft.entity.Student;
import com.pisoft.pisoft.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public List<StudentDTO> findAll() {

        List<Student> studentList = studentRepository.findAll();

//        List<StudentDTO> studentDTOList = new ArrayList<>();
//
//        for(Student student : studentList){
//            StudentDTO studentDTO = modelMapper.map(student, StudentDTO.class);
//            studentDTOList.add(studentDTO);
//        }

        List<StudentDTO> studentDTOList = studentList.stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .toList();
        return studentDTOList;
    }

    public StudentDTO save(Student student) {

        Student student1 = studentRepository.save(student); // insert into student...

        StudentDTO studentDTO = modelMapper.map(student1, StudentDTO.class);

        return studentDTO;
    }

    public StudentDTO findById(Integer id) {

        Optional<Student> optionalStudent = studentRepository.findById(id);

        if (optionalStudent.isPresent()){
            Student student = optionalStudent.get();
            StudentDTO studentDTO = modelMapper.map(student, StudentDTO.class);
            return studentDTO;
        }else {
            return null;
        }
    }


    public Boolean deleteStudentById(Integer studentId) {
        studentRepository.deleteById(studentId); // delete from student where id = ?
        return true;
    }

    public StudentDTO updateWholeStudent(Integer studentId, Student student) {

        Optional<Student> student1 = studentRepository.findById(studentId);

        if (student1.isPresent()){
            student.setId(studentId);
            Student save = studentRepository.save(student);

            StudentDTO studentDTO = modelMapper.map(save, StudentDTO.class);
            return studentDTO;

        }else {
            return null;
        }
    }
}

