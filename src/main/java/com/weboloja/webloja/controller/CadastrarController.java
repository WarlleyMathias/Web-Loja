package com.weboloja.webloja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.weboloja.webloja.service.CadastrarService;

@Controller
public class CadastrarController {
	
    @Autowired
    CadastrarService cadastrarService;

		@RequestMapping(value="/cadastrar", method=RequestMethod.GET)
		public ModelAndView getLogin() {
			ModelAndView mv = new ModelAndView("cadastrar");
			return mv;
		}
		
		@RequestMapping(value="/cadastrar", method=RequestMethod.POST)
		public ModelAndView  setLogin(String nome, String email, String password) {
			String menssagem = cadastrarService.cadastrar(nome, email, password);
			ModelAndView mv = new ModelAndView("cadastrar");
			mv.addObject("menssagem", menssagem);
			return mv;
		}
		
}

