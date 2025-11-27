package com.pisoft.pisoft.controller;

import com.pisoft.pisoft.annotion.Skip;
import com.pisoft.pisoft.entity.Customer;
import com.pisoft.pisoft.entity.Users;
import com.pisoft.pisoft.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hi")
@RequiredArgsConstructor
@Skip
public class CustomerController {

    private final  CustomerRepository customerRepository;

    @PostMapping("/saveCustomer")
    public Customer saveCustomer(@RequestBody Customer customer){

        System.out.println(customer);
        // insert  into table
        return customerRepository.save(customer);
    }

    @GetMapping("/findAll")
    public List<Customer> show(){

        Users users = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(users);

        return customerRepository.findAll();
    }
}
