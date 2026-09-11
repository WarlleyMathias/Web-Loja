package com.weboloja.webloja.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.weboloja.webloja.model.Endereco;
import com.weboloja.webloja.service.EnderecoService;

@RequiredArgsConstructor
@RestController
public class EnderecoController {

	EnderecoService enderecoService;
	
	@RequestMapping(value="/editarEndereco", method=RequestMethod.GET)
	public ModelAndView getEditarEndereco() {
		return enderecoService.getEditarEndereco();
	}

	@RequestMapping(value="/editarEndereco", method=RequestMethod.POST)
	public String setEditarEndereco(Endereco endereco) {
		return enderecoService.update(endereco);
	}
	
	@RequestMapping(value="/criarEndereco", method=RequestMethod.GET)
	public ModelAndView getCriarEndereco() {
        return new ModelAndView("criarEndereco");
	}

	@RequestMapping(value="/criarEndereco", method=RequestMethod.POST)
	public String setCriarEndereco(Endereco endereco) {
		return enderecoService.save(endereco);
	}

}
