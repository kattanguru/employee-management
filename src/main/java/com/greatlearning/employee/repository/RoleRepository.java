package com.greatlearning.employee.repository;

import com.greatlearning.employee.entity.Role;
import com.greatlearning.employee.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    List<Role> findByRoleId(String roleId);

    Optional<Role> findByName(String name);
}
