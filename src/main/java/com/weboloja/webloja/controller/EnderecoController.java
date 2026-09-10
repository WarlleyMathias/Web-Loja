package com.weboloja.webloja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.weboloja.webloja.model.Endereco;
import com.weboloja.webloja.service.EnderecoService;
import com.weboloja.webloja.service.UserService;

@Controller
public class EnderecoController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	EnderecoService enderecoService;
	
	@RequestMapping(value="/editarEndereco", method=RequestMethod.GET)
	public ModelAndView getEditarEndereco() {
		if(enderecoService.findID(userService.usuarioLogado().getId()).equals(null)) {
			ModelAndView mv = new ModelAndView("criarEndereco");
			return mv;
		}
		ModelAndView mv = new ModelAndView("editarEndereco");
		Endereco endereco = enderecoService.findID(userService.usuarioLogado().getId());
		mv.addObject("endereco", endereco);
		return mv;
	}

	@RequestMapping(value="/editarEndereco", method=RequestMethod.POST)
	public String setEditarEndereco(Endereco endereco) {
		enderecoService.update(endereco, userService.usuarioLogado().getId());
		return "redirect:/perfil";	
	}
	
	@RequestMapping(value="/criarEndereco", method=RequestMethod.GET)
	public ModelAndView getCriarEndereco() {
		ModelAndView mv = new ModelAndView("criarEndereco");
		return mv;
	}

	@RequestMapping(value="/criarEndereco", method=RequestMethod.POST)
	public String setCriarEndereco(Endereco endereco) {
		endereco.setIdUser(userService.usuarioLogado().getId());
		enderecoService.save(endereco);
		return "redirect:/carrinho";	
	}

}
