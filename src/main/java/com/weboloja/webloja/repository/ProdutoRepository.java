package com.weboloja.webloja.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weboloja.webloja.model.Produto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository <Produto, Long>{

	List<Produto> findProdutoByCategoria(String categoria);
}
