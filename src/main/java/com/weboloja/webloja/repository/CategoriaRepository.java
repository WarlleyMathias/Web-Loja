package com.weboloja.webloja.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weboloja.webloja.model.Categoria;

@org.springframework.stereotype.Repository
public interface CategoriaRepository extends JpaRepository <Categoria, Long>{

}
