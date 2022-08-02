package com.greatlearning.employee.controller;

import com.greatlearning.employee.entity.Employee;
import com.greatlearning.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping()
    public ResponseEntity<List<Employee>> getAllEmployee() {
        try {
            List<Employee> employees = employeeRepository.findAll();
            if (!employees.isEmpty()) {
                return new ResponseEntity<>(employees, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Employee>> getEmployeeByName(@PathVariable("name") String name, @RequestParam("sort") String sort) {
        try {
            List<Employee> employees = employeeRepository.findByFirstName(name);
            if (null != employees && !employees.isEmpty()) {
                if ("asc".equalsIgnoreCase(sort)) {
                    employees = employees.stream().sorted(Comparator.comparing(Employee::getFirstName)).collect(Collectors.toList());
                } else {
                    employees = employees.stream().sorted(Comparator.comparing(Employee::getFirstName).reversed()).collect(Collectors.toList());
                }
                return new ResponseEntity<>(employees, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Integer id) {
        try {
            Optional<Employee> employee = employeeRepository.findById(id);
            return employee.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        try {
            employeeRepository.save(employee);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping()
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        try {
            employeeRepository.save(employee);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") Integer id) {
        try {
            employeeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping()
    public ResponseEntity<HttpStatus> deleteAllEmployee() {
        try {
            employeeRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{email}")
    public ResponseEntity<List<Employee>> findByEmail(@PathVariable("email") String email) {
        try {
            List<Employee> employee = employeeRepository.findByEmail(email);
            if (null != employee && !employee.isEmpty()) {
                return new ResponseEntity<>(employee, HttpStatus.OK);
            }
            return new ResponseEntity<>(employee, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
