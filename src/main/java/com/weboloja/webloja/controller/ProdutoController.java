package com.weboloja.webloja.controller;

import java.io.IOException;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.weboloja.webloja.model.Produto;
import com.weboloja.webloja.service.ProdutoService;

@RequiredArgsConstructor
@RestController
public class ProdutoController {

	ProdutoService produtoService;

	@GetMapping ("/produto/{id}")
	public ModelAndView getProduto(@PathVariable("id") long id) {
		return produtoService.findProdutos(id);
	}
	
	@GetMapping ("/addproduto")
	public ModelAndView getAddproduto() {
		return produtoService.getCategoriaProduto();
	}

	@PostMapping("/addproduto")
	public String setAddproduto(@ModelAttribute Produto produto, MultipartFile multipartFile) throws IOException {
		return produtoService.save(produto, multipartFile);
	}

}
