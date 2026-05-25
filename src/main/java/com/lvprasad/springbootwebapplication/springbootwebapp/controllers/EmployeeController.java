package com.lvprasad.springbootwebapplication.springbootwebapp.controllers;


import com.lvprasad.springbootwebapplication.springbootwebapp.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

//    @GetMapping(path = "/getSecretMessage")
//    public String getMySuperSecretMessage() {
//        return "Hello World";
//    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable(name = "employeeId") Long Id) {
        return new EmployeeDTO(Id, "Lakshmi vara Prasad", "lvp@gmail.com", 27, LocalDate.of(2026, 5, 28),true);
    }

    @GetMapping
    public String getAllEmployees(@RequestParam(required = false, name = "inputAge") Integer age,
                                  @RequestParam(required = false) String sortBy) {
        return "Lakshmi vara Prasad" + age + " " + sortBy;
    }

    @PostMapping
    public EmployeeDTO  createEmployee(@RequestBody EmployeeDTO inputEmployee) {
        inputEmployee.setId(100L);
        return inputEmployee;
    }

    @PutMapping
    public String updateEmployee() {
        return "Lakshmi vara Prasad from put";
    }

}
