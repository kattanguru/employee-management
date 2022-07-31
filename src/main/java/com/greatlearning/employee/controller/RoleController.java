package com.greatlearning.employee.controller;

import com.greatlearning.employee.entity.Role;
import com.greatlearning.employee.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleRepository roleRepository;

    @GetMapping()
    public ResponseEntity<List<Role>> getAllRoles() {
        try {
            List<Role> roles = roleRepository.findAll();
            if (!roles.isEmpty()) {
                return new ResponseEntity<>(roles, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable("id") Integer id) {
        try {
            Optional<Role> role = roleRepository.findById(id);
            return role.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        try {
            roleRepository.save(role);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping()
    public ResponseEntity<Role> updateRole(@RequestBody Role role) {
        try {
            roleRepository.save(role);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteRole(@PathVariable("id") Integer id) {
        try {
            roleRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
