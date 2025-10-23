package com.pisoft.pisoft.controller;

import com.pisoft.pisoft.entity.Student;
import com.pisoft.pisoft.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor  // it will create all const.. on your behalf..
public class StudentController {

    private final StudentRepository studentRepository;

    @GetMapping("/getAll")
    public List<Student> studentList(){
        return studentRepository.findAll();
    }

    @PostMapping("/saveStudent")
    public Student saveStudent(@RequestBody Student student){
        return studentRepository.save(student);
    }

    @GetMapping("/getById/{studentId}")
    public Student studentList(@PathVariable(value = "studentId") Integer id){
        Optional<Student> studentOptional = studentRepository.findById(id);
        return studentOptional.get();
    }


}
