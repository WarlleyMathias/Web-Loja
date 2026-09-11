package com.weboloja.webloja.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weboloja.webloja.model.Endereco;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository <Endereco, Long>{

	Endereco findEnderecoByIdUser(Long idUser);

	boolean existsByIdUser(Long idUser);
}

