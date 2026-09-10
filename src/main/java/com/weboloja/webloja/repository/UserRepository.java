package com.weboloja.webloja.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weboloja.webloja.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}