package com.weboloja.webloja.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.weboloja.webloja.config.FileUploadUtil;
import com.weboloja.webloja.model.Categoria;
import com.weboloja.webloja.model.Produto;
import com.weboloja.webloja.model.User;
import com.weboloja.webloja.service.CategoriaService;
import com.weboloja.webloja.service.ProdutoService;
import com.weboloja.webloja.service.UserService;

@Controller
public class ProdutoController {
	
	@Autowired
	ProdutoService produtoService;
	
	@Autowired
	CategoriaService categoriaService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/produto/{id}", method=RequestMethod.GET)
	public ModelAndView getProduto(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("produto");
		Produto produto = produtoService.findID(id);
		List<Integer> itens = new ArrayList<Integer>(produto.getQuantidade());
		for(int i=0; i < produto.getQuantidade(); i++)
		itens.add(i, i+1);
		User user = null;
		try {
		user = userService.usuarioLogado();
		}catch(Exception e) {	
		}
		mv.addObject("user", user);
		mv.addObject("produto", produto);
		mv.addObject("itens", itens);
		return mv;
	}
	
	@RequestMapping(value="/addproduto", method=RequestMethod.GET)
	public ModelAndView getAddproduto() {
		ModelAndView mv = new ModelAndView("addproduto");
		List <Categoria> categorias = categoriaService.findAll();
		mv.addObject("categorias", categorias);
		return mv;
	}
	
	@RequestMapping(value="/addproduto", method=RequestMethod.POST)
	public String setAddproduto(String Nome, Float Valor, String Descricao, int Quantidade, String Categoria, @RequestParam("Imagem") MultipartFile multipartFile) throws IOException{

		String Imagem = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		
		Produto produto = new Produto();
		produto.setNome(Nome);
		produto.setValor(Valor);
		produto.setDescricao(Descricao);
		produto.setImagem(Imagem);
		produto.setQuantidade(Quantidade);
		produto.setCategoria(Categoria);
		
		Produto produtoSalvo = produtoService.save(produto);
		
		String uploadDir = "user-photos/" + produtoSalvo.getId();
		 
        FileUploadUtil.saveFile(uploadDir, Imagem, multipartFile);
		
		return "redirect:/addproduto";
	}

}
