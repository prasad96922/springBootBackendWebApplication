package com.lvprasad.springbootwebapplication.springbootwebapp.services;


import com.lvprasad.springbootwebapplication.springbootwebapp.dto.EmployeeDTO;
import com.lvprasad.springbootwebapplication.springbootwebapp.entities.EmployeeEntity;
import com.lvprasad.springbootwebapplication.springbootwebapp.repositories.EmployeeRepository;
import org.apache.el.util.ReflectionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

 private final EmployeeRepository employeeRepository;
 private final ModelMapper modelMapper;

 public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
  this.employeeRepository = employeeRepository;
  this.modelMapper = modelMapper;
 }

 public Optional<EmployeeDTO> getEmployeeById(Long id) {
////  return employeeRepository.findById(id).orElse(null);
////  EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
//////  ModelMapper modelMapper = new ModelMapper();
//////  EmployeeDTO employeeDTO = modelMapper.map(employeeEntity, EmployeeDTO.class);
////  EmployeeDTO employeeDTO = modelMapper.map(employeeEntity, EmployeeDTO.class);
////  return employeeDTO;
  return employeeRepository.findById(id).map(employeeEntity ->  modelMapper.map(employeeEntity, EmployeeDTO.class));

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

 public EmployeeDTO updateEmployeeById(Long id, EmployeeDTO employeeDTO) {
   EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
   employeeEntity.setId(id);
   EmployeeEntity  saveEmployeeEntity =  employeeRepository.save(employeeEntity);
   return  modelMapper.map(saveEmployeeEntity, EmployeeDTO.class);
 }

 public boolean isExistingEmployeeById(Long id) {
  return employeeRepository.existsById(id);
 }

 public String  deleteByEmployeeById(Long id) {
  boolean exists = isExistingEmployeeById(id);
  if (!exists) return "Employee not found";
  employeeRepository.deleteById(id);
  return "Employee deleted successfully";
 }

 public EmployeeDTO updatePartialEmployeeById(Long id, Map<String, Object> updates) {
  boolean exists = isExistingEmployeeById(id);
  if (!exists) return null;
  EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
  updates.forEach((field, value) -> {
    Field fieldToBeUpdated = ReflectionUtils.findField(EmployeeEntity.class, field);
    fieldToBeUpdated.setAccessible(true);
    ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
  });
  employeeRepository.save(employeeEntity);
  return modelMapper.map(employeeEntity, EmployeeDTO.class);
 }
}
