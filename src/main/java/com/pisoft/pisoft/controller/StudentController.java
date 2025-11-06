package com.pisoft.pisoft.controller;

import com.pisoft.pisoft.annotion.Skip;
import com.pisoft.pisoft.dto.StudentDTO;
import com.pisoft.pisoft.entity.Customer;
import com.pisoft.pisoft.entity.Student;
import com.pisoft.pisoft.exception.ResourceNotFound;
import com.pisoft.pisoft.repository.CustomerRepository;
import com.pisoft.pisoft.service.StudentService;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
@Skip
public class StudentController {

    private final StudentService studentService;
    private final EntityManager entityManager;

    @GetMapping("/getAll")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @PostMapping("/saveStudent")
    public ResponseEntity<StudentDTO> saveStudent(@RequestBody @Valid Student student) {
        StudentDTO studentDTO = studentService.save(student);
        return new ResponseEntity<>(studentDTO, HttpStatus.CREATED);
    }

    @GetMapping("/getById/{studentId}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Integer studentId) {
        StudentDTO studentDTO = studentService.findByStudentId(studentId);
        return ResponseEntity.ok(studentDTO);
    }

    @DeleteMapping("/deleteStudent/{studentId}")
    public ResponseEntity<String> deleteStudentById(@PathVariable Integer studentId) {
        studentService.deleteStudentById(studentId);
        return ResponseEntity.ok("Student successfully deleted");
    }


    @PutMapping("/updateStudent/{studentId}")
    public ResponseEntity<StudentDTO> updateStudent(
            @PathVariable Integer studentId,
            @RequestBody Student student) {
        StudentDTO updatedStudent = studentService.updateWholeStudent(studentId, student);
        return ResponseEntity.ok(updatedStudent);
    }

    private final CustomerRepository customerRepository;
    @PostMapping("/saveCustomer")
    public Customer saveCustomer(@RequestBody Customer customer){

        System.out.println(customer);
        // insert  into table
        return customerRepository.save(customer);
    }
}
