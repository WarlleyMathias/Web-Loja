package com.weboloja.webloja.controller;

import com.weboloja.webloja.service.ProdutosService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.weboloja.webloja.model.Categoria;

@RequiredArgsConstructor
@RestController
public class ProdutosController {

	ProdutosService produtosService;
	
	@GetMapping("/produtos/{categoriaAtual}/{pagina}")
	public ModelAndView getProdutos(@PathVariable("categoriaAtual") String categoriaAtual, @PathVariable("pagina") int pagina) {
		return produtosService.getProdutos(categoriaAtual,pagina);
	}
	
	@PostMapping(value="/addcategoria")
	public String setAddcategoria(Categoria categoria) {
		return produtosService.addCategoria(categoria);
	}

}
