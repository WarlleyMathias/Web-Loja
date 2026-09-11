package com.weboloja.webloja.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LocalizacaoController {
	
	@GetMapping("/localizacao")
	public ModelAndView getLocalizacao() {
        return new ModelAndView("localizacao");
	}

}
