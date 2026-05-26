package com.lvprasad.springbootwebapplication.springbootwebapp.controllers;


import com.lvprasad.springbootwebapplication.springbootwebapp.dto.EmployeeDTO;
import com.lvprasad.springbootwebapplication.springbootwebapp.entities.EmployeeEntity;
import com.lvprasad.springbootwebapplication.springbootwebapp.repositories.EmployeeRepository;
import com.lvprasad.springbootwebapplication.springbootwebapp.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//import java.time.LocalDate;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
//    private final EmployeeRepository employeeRepository;
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

//    public EmployeeController(EmployeeRepository employeeRepository) {
//        this.employeeRepository = employeeRepository;
//    }

//    @GetMapping(path = "/getSecretMessage")
//    public String getMySuperSecretMessage() {
//        return "Hello World";
//    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable(name = "employeeId") Long id) {
        return employeeService.getEmployeeById(id);
    }


    @GetMapping
    public List<EmployeeDTO> getAllEmployees(@RequestParam(required = false, name = "inputAge") Integer age,
                                                @RequestParam(required = false) String sortBy) {
          return employeeService.getAllEmployees();
//        return employeeRepository.findAll();
    }

    @PostMapping
    public EmployeeDTO  createNewEmployee(@RequestBody EmployeeDTO inputEmployee) {
//      return employeeRepository.save(inputEmployee);
        return employeeService.createNewEmployee(inputEmployee);
    }

    @PutMapping
    public String updateEmployee() {
        return "Lakshmi vara Prasad from put";
    }

}
