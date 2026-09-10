package com.weboloja.webloja.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.weboloja.webloja.model.Produto;
import com.weboloja.webloja.service.ProdutoService;

@org.springframework.stereotype.Controller
public class Controller {
	
	@Autowired
	ProdutoService produtoService;

	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView getPrincipal() {
		ModelAndView mv = new ModelAndView("principal");
		List <Produto> produtos = produtoService.findAll();
		List <Produto> produtos1 = new ArrayList<Produto>();
		List <Produto> produtos2 = new ArrayList<Produto>();
		Random random = new Random();
		for(int i=0; i < 6 && produtos.size() > 0; i++) {
			int r1 = random.nextInt(produtos.size());
			produtos1.add(produtos.get(r1));
			produtos.remove(r1);
		}
		for(int i=0; i < 6 && produtos.size() > 0; i++) {
			int r2 = random.nextInt(produtos.size());
			produtos2.add(produtos.get(r2));
			produtos.remove(r2);
		}
		mv.addObject("produtos1", produtos1);
		mv.addObject("produtos2", produtos2);
		return mv;
	}
	
	
	

	

	
	
	 
}
