package com.weboloja.webloja.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController {

	@GetMapping("/login")
	public ModelAndView getLogin() {
        return new ModelAndView("login");
	}
	
	@PostMapping("/login")
	public ModelAndView setLogin() {
        return new ModelAndView("login");
	}
	
}
