package com.lvprasad.springbootwebapplication.springbootwebapp.controllers;


import com.lvprasad.springbootwebapplication.springbootwebapp.dto.EmployeeDTO;
import com.lvprasad.springbootwebapplication.springbootwebapp.entities.EmployeeEntity;
import com.lvprasad.springbootwebapplication.springbootwebapp.exceptions.ResourceNotFoundException;
import com.lvprasad.springbootwebapplication.springbootwebapp.repositories.EmployeeRepository;
import com.lvprasad.springbootwebapplication.springbootwebapp.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

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
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "employeeId") Long id) {
//        EmployeeDTO employeeDTO =  employeeService.getEmployeeById(id);
//        if (employeeDTO == null) return ResponseEntity.notFound().build();
//        return ResponseEntity.ok(employeeDTO);
        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(id);
//        System.out.println("ResponseEntity<EmployeeDTO>===>" + employeeDTO.map(employee -> employee.getName()));
        return employeeDTO
                .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElseThrow(()-> new ResourceNotFoundException("Resource with id " + id + " not found" + ":" + id));
    }

//    @ExceptionHandler(NoSuchElementException.class)
//    public ResponseEntity<String> handleEmployeeNotFound(NoSuchElementException e) {
////        return e.getMessage();
//        return new  ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//    }



    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(required = false, name = "inputAge") Integer age,
                                                @RequestParam(required = false) String sortBy) {
//          return employeeService.getAllEmployees();
//        return employeeRepository.findAll();
//        ResponseEntity<List<EmployeeDTO>> employeeList = ResponseEntity.ok(employeeService.getAllEmployees());
//        System.out.println(employeeList.toString());
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO>  createNewEmployee(@RequestBody @Valid EmployeeDTO inputEmployee) {
//      return employeeRepository.save(inputEmployee);
//        return employeeService.createNewEmployee(inputEmployee);
        EmployeeDTO savedEmployee = employeeService.createNewEmployee(inputEmployee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }


    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody @Valid EmployeeDTO employeeDTO,
                                 @PathVariable(name = "employeeId") Long id) {
//        return employeeService.updateEmployeeById(id,employeeDTO);
        return ResponseEntity.ok(employeeService.updateEmployeeById(id, employeeDTO));
    }

    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<String> deleteByEmployeeById(@PathVariable(name = "employeeId") Long id) {
//        return employeeService.deleteByEmployeeById(id);
        return ResponseEntity.ok(employeeService.deleteByEmployeeById(id));
    }

    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@RequestBody Map<String, Object> updates,
                                          @PathVariable(name = "employeeId") Long id) {
//        return employeeService.updatePartialEmployeeById(id,updates);
        EmployeeDTO savedEmployee = employeeService.updatePartialEmployeeById(id, updates);
        if (savedEmployee == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(savedEmployee);
    }

}
