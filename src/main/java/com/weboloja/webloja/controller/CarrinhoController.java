package com.weboloja.webloja.controller;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.weboloja.webloja.config.Compra;
import com.weboloja.webloja.model.Carrinho;
import com.weboloja.webloja.model.Endereco;
import com.weboloja.webloja.model.Produto;
import com.weboloja.webloja.service.CarrinhoService;
import com.weboloja.webloja.service.EnderecoService;
import com.weboloja.webloja.service.ProdutoService;
import com.weboloja.webloja.service.UserService;

@Controller
public class CarrinhoController {
	
	@Autowired
	ProdutoService produtoService;
	
	@Autowired
	CarrinhoService carrinhoService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	EnderecoService enderecoService;

	@RequestMapping(value="/carrinho", method=RequestMethod.GET)
	public ModelAndView getCarrinho() {
		ModelAndView mv = new ModelAndView("carrinho");
		List <Carrinho> carrinho = carrinhoService.findCarrinho(userService.usuarioLogado().getId());
		List <Produto> produtos = new ArrayList<Produto>();
		for(int i=0; i < carrinho.size(); i++){
			produtos.add(produtoService.findID(carrinho.get(i).getIdProduto()));
		}
		mv.addObject("produtos", produtos);
		mv.addObject("carrinho", carrinho);
		return mv;
	}
	
	@RequestMapping(value="/addcarrinho", method=RequestMethod.POST)
	public String setAddcarrinho(Carrinho carrinho) {
		carrinho.setIdUsuario(userService.usuarioLogado().getId());
		if(carrinhoService.findItem(carrinho) != null)
			carrinhoService.updateCarrinho(carrinho.getQuantidade(), carrinho);
		else
		carrinhoService.save(carrinho);
		return "redirect:/produto/" + carrinho.getIdProduto().toString();
	}
	
	@RequestMapping(value="/addcarrinho2", method=RequestMethod.POST)
	public String setAddcarrinho2(Carrinho carrinho) {
		carrinho.setIdUsuario(userService.usuarioLogado().getId());
		if(carrinhoService.findItem(carrinho) != null)
			carrinhoService.updateCarrinho(carrinho.getQuantidade(), carrinho);
		else
		carrinhoService.save(carrinho);
		return "redirect:/carrinho";
	}
	
	@RequestMapping(value="/dropcarrinho", method=RequestMethod.POST)
	public String dropCarrinho(Long IdUsuario, Long IdProduto) {
		carrinhoService.dropCarrinho(IdUsuario, IdProduto);
		return "redirect:/carrinho";	
	}
	
	@RequestMapping(value="/finalizarcompra", method=RequestMethod.GET)
	public ModelAndView finalizaCompra() {
		ModelAndView mv = new ModelAndView("carrinho");
		List <Carrinho> carrinho = carrinhoService.findCarrinho(userService.usuarioLogado().getId());
		List <Produto> produtos = new ArrayList<Produto>();
		for(int i=0; i < carrinho.size(); i++){
				produtos.add(produtoService.findID(carrinho.get(i).getIdProduto()));
		}
		Endereco endereco = enderecoService.findID(userService.usuarioLogado().getId());
		if(produtos.size()==0 || endereco.getBairro()==null) {
			if(produtos.size()==0)
				mv.addObject("menssagem", "Selecione um produto antes de finalizar a compra.");
			else
				mv.addObject("menssagem", "É necessário criar um endereço antes.");
		}else {
		try {
			Compra c = new Compra();
			String codigo = c.comprar(endereco, userService.usuarioLogado(), carrinho, produtos);
			mv.addObject("codigo", codigo);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		}
		mv.addObject("produtos", produtos);
		mv.addObject("carrinho", carrinho);
		return mv;
			
	}
	
}
