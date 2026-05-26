package com.lvprasad.springbootwebapplication.springbootwebapp.controllers;


import com.lvprasad.springbootwebapplication.springbootwebapp.dto.EmployeeDTO;
import com.lvprasad.springbootwebapplication.springbootwebapp.entities.EmployeeEntity;
import com.lvprasad.springbootwebapplication.springbootwebapp.repositories.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//import java.time.LocalDate;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;


    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

//    @GetMapping(path = "/getSecretMessage")
//    public String getMySuperSecretMessage() {
//        return "Hello World";
//    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeEntity getEmployeeById(@PathVariable(name = "employeeId") Long id) {
        return employeeRepository.findById(id).orElse(null);
    }


    @GetMapping
    public List<EmployeeEntity> getAllEmployees(@RequestParam(required = false, name = "inputAge") Integer age,
                                                @RequestParam(required = false) String sortBy) {
        return employeeRepository.findAll();
    }

    @PostMapping
    public EmployeeEntity  createNewEmployee(@RequestBody EmployeeEntity inputEmployee) {
      return employeeRepository.save(inputEmployee);
    }

    @PutMapping
    public String updateEmployee() {
        return "Lakshmi vara Prasad from put";
    }

}
