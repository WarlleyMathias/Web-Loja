package com.weboloja.webloja.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weboloja.webloja.model.Carrinho;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrinhoRepository extends JpaRepository <Carrinho, Long>{

	Carrinho findByIdUsuarioAndIdProduto(Long idUsuario, Long idProduto);

	List<Carrinho> findCarrinhoByIdUsuario(Long idUsuario);
}
