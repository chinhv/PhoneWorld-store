package com.group16.mystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group16.mystore.entities.Role;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByRoleName(String roleName);
}
