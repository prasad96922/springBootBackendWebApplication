package com.lvprasad.springbootwebapplication.springbootwebapp.services;


import com.lvprasad.springbootwebapplication.springbootwebapp.dto.EmployeeDTO;
import com.lvprasad.springbootwebapplication.springbootwebapp.entities.EmployeeEntity;
import com.lvprasad.springbootwebapplication.springbootwebapp.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

 private final EmployeeRepository employeeRepository;
 private final ModelMapper modelMapper;

 public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
  this.employeeRepository = employeeRepository;
  this.modelMapper = modelMapper;
 }

 public EmployeeDTO getEmployeeById(Long id) {
//  return employeeRepository.findById(id).orElse(null);
  EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
//  ModelMapper modelMapper = new ModelMapper();
//  EmployeeDTO employeeDTO = modelMapper.map(employeeEntity, EmployeeDTO.class);
  EmployeeDTO employeeDTO = modelMapper.map(employeeEntity, EmployeeDTO.class);
  return employeeDTO;

 }

 public List<EmployeeDTO> getAllEmployees() {
  List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();
  return employeeEntityList.
          stream().
          map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class))
          .collect(Collectors.toList());

 }

 public EmployeeDTO createNewEmployee(EmployeeDTO inputEmployee) {
  // to check if user or admin
  // log something
  EmployeeEntity toSaveEntity = modelMapper.map(inputEmployee, EmployeeEntity.class);
  EmployeeEntity savedEmployeeEntity = employeeRepository.save(toSaveEntity);
  return  modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
 }
}
