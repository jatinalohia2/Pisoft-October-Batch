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

    @GetMapping("/getEmp/{empId}/{name}/{salary}")
    public EmployeeDto getEmployeeById(@PathVariable Integer empId ,
                                       @PathVariable String name ,
                                       @PathVariable Double salary){

        return new EmployeeDto(empId , name , salary);
    }


    @GetMapping("/getEmp2")
    public String getEmployeeById2(
            @RequestParam(required = false , value = "empId") String id,
            @RequestParam(required = false , value = "n") String name){
        return "this is my id : "+id + " : "+name;
    }


    @PostMapping("/saveEmp")
    public String  saveEmployee(@RequestBody String name){
        System.out.println(name);
        return name;
    }





}
