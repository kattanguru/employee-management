package com.greatlearning.employee.repository;


import com.greatlearning.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findByEmail(String email);

    List<Employee> findByFirstName(String firstName);


}
