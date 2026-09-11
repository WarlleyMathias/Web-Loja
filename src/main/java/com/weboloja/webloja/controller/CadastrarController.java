package com.weboloja.webloja.controller;

import com.weboloja.webloja.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.weboloja.webloja.service.CadastrarService;

@RequiredArgsConstructor
@RestController
public class CadastrarController {

    private final CadastrarService cadastrarService;

		@GetMapping("/cadastrar")
		public ModelAndView getLogin() {
            return new ModelAndView("cadastrar");
		}
		
		@PostMapping("/cadastrar")
		public ModelAndView  setLogin(@ModelAttribute User user) {
			return cadastrarService.cadastrar(user);
		}
		
}

