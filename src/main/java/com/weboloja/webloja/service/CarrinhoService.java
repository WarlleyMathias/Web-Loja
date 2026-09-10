package com.weboloja.webloja.service;

import com.weboloja.webloja.model.Carrinho;
import com.weboloja.webloja.repository.CarrinhoRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;



@org.springframework.stereotype.Service
public class CarrinhoService {
	
	@Autowired
	CarrinhoRepository cr;
	
	public List<Carrinho> findAll() {
		return cr.findAll();		
	}
	
	public Carrinho findID(Long id) {
		return cr.findById(id).get();
	}
	
	public Carrinho save(Carrinho carrinho) {
		return cr.save(carrinho);
	}

	public Carrinho findItem(Carrinho carrinho){
		return cr.findItem(carrinho.getIdUsuario(), carrinho.getIdProduto());
	}
	
	public void updateCarrinho(int quantidade, Carrinho carrinho) {
		cr.updateCarrinho(quantidade, carrinho.getIdUsuario(), carrinho.getIdProduto());
	}
	
	public void dropCarrinho(Long IdUsuario, Long IdProduto) {
		cr.dropCarrinho(IdUsuario, IdProduto);
	}
	
	public List<Carrinho> findCarrinho(Long IdUsuario) {
		return cr.findCarrinho(IdUsuario);
				
	}
}
