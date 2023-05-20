package com.myproject.mystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.mystore.entities.Role;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByRoleName(String roleName);
}
