package com.pisoft.pisoft;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {



    @GetMapping(path = "/secretKey")
    public String getSecretKey(){
        return "jatinabc";
    }

    @GetMapping(path = "/getAge")
    public String getAge(){
        return "12";
    }

    @GetMapping(path = "/getSalary")
    public String getSalary(){
        return "1200";
    }

    @GetMapping("/getEmp/{empId}/{emp}")
    public EmployeeDto getEmployeeById(@PathVariable Integer empId ,
                                       @PathVariable(name = "emp") Integer e){

        System.out.println("inside");

        return new EmployeeDto(empId + e, "jatin" , 1200.00);
    }


    @GetMapping("/getEmp2")
    public String getEmployeeById2(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String name){
        return "this is my id : "+id + " : "+name;
    }






}
