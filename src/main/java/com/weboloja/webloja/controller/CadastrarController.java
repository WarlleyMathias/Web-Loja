package com.weboloja.webloja.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.weboloja.webloja.service.CadastrarService;

@RestController
@RequiredArgsConstructor
public class CadastrarController {

    private final CadastrarService cadastrarService;

		@RequestMapping(value="/cadastrar", method=RequestMethod.GET)
		public ModelAndView getLogin() {
            return new ModelAndView("cadastrar");
		}
		
		@RequestMapping(value="/cadastrar", method=RequestMethod.POST)
		public ModelAndView  setLogin(String nome, String email, String password) {
			String menssagem = cadastrarService.cadastrar(nome, email, password);
			ModelAndView mv = new ModelAndView("cadastrar");
			mv.addObject("menssagem", menssagem);
			return mv;
		}
		
}

