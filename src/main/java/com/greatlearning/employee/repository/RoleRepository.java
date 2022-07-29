package com.greatlearning.employee.repository;

import com.greatlearning.employee.entity.Role;
import com.greatlearning.employee.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<User> findByRoleId(String roleId);

    Optional<User> findByName(String name);
}
