package com.weboloja.webloja.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weboloja.webloja.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}