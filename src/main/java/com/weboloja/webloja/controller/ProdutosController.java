package com.weboloja.webloja.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.weboloja.webloja.model.Categoria;
import com.weboloja.webloja.model.Produto;
import com.weboloja.webloja.service.CategoriaService;
import com.weboloja.webloja.service.ProdutoService;

@Controller
public class ProdutosController {
	
	@Autowired
	CategoriaService categoriaService;
	
	@Autowired
	ProdutoService produtoService;
	
	@RequestMapping(value="/produtos/{categoriaAtual}/{pagina}", method=RequestMethod.GET)
	public ModelAndView getProdutos(@PathVariable("categoriaAtual") String categoriaAtual, @PathVariable("pagina") int pagina) {
		ModelAndView mv = new ModelAndView("produtos");
		
		List <Produto> produtos = null;
		
		if(categoriaAtual.equals("Todos"))
			produtos = produtoService.findAll();
		else
			produtos =produtoService.findCategoria(categoriaAtual);
		
		int pag = produtos.size() / 18;
		
		if(produtos.size() % 18 != 0)
			pag++;
		
		List <Integer> paginas = new ArrayList<Integer>(Collections.nCopies(pag,0));
		List <Categoria> categorias = categoriaService.findAll();
		
		mv.addObject("categorias", categorias);
		mv.addObject("categoriaAtual", categoriaAtual);
		mv.addObject("pagina", pagina);
		mv.addObject("paginas", paginas);
		mv.addObject("produtos", produtos);
		return mv;
	}
	
	@RequestMapping(value="/addcategoria", method=RequestMethod.POST)
	public String setAddcategoria(Categoria categoria) {
		categoriaService.save(categoria);
		return "redirect:/produtos/Todos/1";	
	}

}
