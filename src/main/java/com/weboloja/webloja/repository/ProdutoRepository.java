package com.weboloja.webloja.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.weboloja.webloja.model.Produto;

@org.springframework.stereotype.Repository
public interface ProdutoRepository extends JpaRepository <Produto, Long>{
	
	@Modifying
	@Transactional
	@Query(value = "insert into public.produto (nome, imagem, valor, descricao) values (:nome, :imagem, :valor, :descricao)", nativeQuery = true)
	void insertUser(@Param("nome") String nome, @Param("imagem") String imagem, @Param("valor") Float valor, @Param("descricao") String descricao);
	
	@Query(value = "SELECT * FROM public.tb_produto where categoria = :categoria", nativeQuery = true)
	List<Produto> findCategoria(@Param("categoria") String categoria);
	
}
