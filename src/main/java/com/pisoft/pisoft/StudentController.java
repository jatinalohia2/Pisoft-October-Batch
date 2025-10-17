package com.pisoft.pisoft;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/student")
public class StudentController {

    @GetMapping(path = "/secretKey")
    public String getSecretKey(){
        return "abc";
    }
}
