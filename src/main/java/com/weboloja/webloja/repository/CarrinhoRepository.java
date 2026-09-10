package com.weboloja.webloja.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.weboloja.webloja.model.Carrinho;

@org.springframework.stereotype.Repository
public interface CarrinhoRepository extends JpaRepository <Carrinho, Long>{
	
	@Query(value ="SELECT * FROM public.tb_carrinho WHERE id_usuario = :id_usuario AND id_produto = :id_produto", nativeQuery = true)
	Carrinho findItem(@Param("id_usuario")Long id_usuario, @Param("id_produto")Long id_produto);
	
	@Query(value ="SELECT * FROM public.tb_carrinho WHERE id_usuario = :id_usuario", nativeQuery = true)
	List <Carrinho> findCarrinho(@Param("id_usuario")Long id_usuario);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE public.tb_carrinho SET quantidade = quantidade + :quantidade WHERE id_usuario = :id_usuario AND id_produto = :id_produto", nativeQuery = true)
	void updateCarrinho(@Param("quantidade") int quantidade, @Param("id_usuario") Long id_usuario, @Param("id_produto") Long id_produto);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM public.tb_carrinho WHERE id_usuario = :id_usuario AND id_produto = :id_produto", nativeQuery = true)
	void dropCarrinho(@Param("id_usuario") Long id_usuario, @Param("id_produto") Long id_produto);
	
}
