package com.lvprasad.springbootwebapplication.springbootwebapp.repositories;

import com.lvprasad.springbootwebapplication.springbootwebapp.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

//    List<EmployeeDTO> findByName(String name);

}
