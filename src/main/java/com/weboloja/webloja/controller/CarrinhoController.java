package com.weboloja.webloja.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.weboloja.webloja.model.Carrinho;
import com.weboloja.webloja.service.CarrinhoService;
import com.weboloja.webloja.service.UserService;

@RequiredArgsConstructor
@Controller
public class CarrinhoController {

	CarrinhoService carrinhoService;

	UserService userService;

	@RequestMapping(value="/carrinho", method=RequestMethod.GET)
	public ModelAndView getCarrinho() {
		return carrinhoService.getCarrinho(userService.usuarioLogado().getId());
	}
	
	@RequestMapping(value="/addcarrinho", method=RequestMethod.POST)
	public String setAddcarrinho(Carrinho carrinho) {
		return carrinhoService.updateCarrinho1(carrinho, userService.usuarioLogado().getId());
	}
	
	@RequestMapping(value="/addcarrinho2", method=RequestMethod.POST)
	public String setAddcarrinho2(Carrinho carrinho) {
		return carrinhoService.updateCarrinho2(carrinho, userService.usuarioLogado().getId());
	}
	
	@RequestMapping(value="/dropcarrinho", method=RequestMethod.POST)
	public String dropCarrinho(Long IdUsuario, Long IdProduto) {
		return carrinhoService.dropCarrinho(IdUsuario, IdProduto);
	}
	
	@RequestMapping(value="/finalizarcompra", method=RequestMethod.GET)
	public ModelAndView finalizaCompra() {
		return carrinhoService.finalizaCompra(userService.usuarioLogado());
	}
	
}
