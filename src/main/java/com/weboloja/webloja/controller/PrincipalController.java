package com.weboloja.webloja.controller;

import com.weboloja.webloja.service.PrincipalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@RestController
public class PrincipalController {

	PrincipalService principalService;

	@GetMapping("/")
	public ModelAndView getPrincipal() {
		return principalService.getPrincipal();
	}
}
