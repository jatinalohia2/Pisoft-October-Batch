package com.pisoft.pisoft.controller;

import com.pisoft.pisoft.dto.StudentDTO;
import com.pisoft.pisoft.entity.Student;
import com.pisoft.pisoft.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor  // it will create all const.. on your behalf..
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/getAll")
    public List<StudentDTO> studentList(){

        return studentService.findAll();
    }

    @PostMapping("/saveStudent")
    public StudentDTO saveStudent(@RequestBody Student student){
        return studentService.save(student);
    }

    @GetMapping("/getById/{studentId}")
    public StudentDTO studentList(@PathVariable(value = "studentId") Integer id){
        StudentDTO studentDTO = studentService.findById(id);
        return studentDTO;
    }

    @DeleteMapping("/deleteStudent/{studentId}")
    public Boolean deleteStudentById(@PathVariable Integer studentId){

        Boolean b = studentService.deleteStudentById(studentId);
        return b;
    }

    @PutMapping("/updateStudent/{studentId}")
    public StudentDTO FullyUpdateStudent(@PathVariable Integer studentId
                                         ,@RequestBody Student student){

        StudentDTO studentDTO = studentService.updateWholeStudent(studentId, student);
        return  studentDTO;

    }


}
