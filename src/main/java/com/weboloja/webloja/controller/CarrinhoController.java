package com.weboloja.webloja.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.weboloja.webloja.model.Carrinho;
import com.weboloja.webloja.service.CarrinhoService;

@RequiredArgsConstructor
@RestController
public class CarrinhoController {

	CarrinhoService carrinhoService;

	@GetMapping("/carrinho")
	public ModelAndView getCarrinho() {
		return carrinhoService.getCarrinho();
	}

	@PostMapping(value="/addcarrinho")
	public String setAddcarrinho(Carrinho carrinho) {
		return carrinhoService.updateCarrinho1(carrinho);
	}

	@PostMapping(value="/addcarrinho2")
	public String setAddcarrinho2(Carrinho carrinho) {
		return carrinhoService.updateCarrinho2(carrinho);
	}
	
	@PostMapping("/dropcarrinho")
	public String dropCarrinho(Long IdUsuario, Long IdProduto) {
		return carrinhoService.dropCarrinho(IdUsuario, IdProduto);
	}
	
	@GetMapping("/finalizarcompra")
	public ModelAndView finalizaCompra() {
		return carrinhoService.finalizaCompra();
	}
	
}
