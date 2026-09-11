package com.weboloja.webloja.controller;

import com.weboloja.webloja.service.PerfilService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@RestController
public class PerfilController {

	PerfilService perfilService;
	
	@GetMapping("/perfil")
	public ModelAndView getPerfil() {
		return perfilService.getPerfil();
	}
	
	@PostMapping("/perfil")
	public String setPerfil() {
		return "redirect:/perfil";	
	}
	
}
