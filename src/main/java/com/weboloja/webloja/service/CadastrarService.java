package com.weboloja.webloja.service;

import java.util.List;

import lombok.RequiredArgsConstructor;

import com.weboloja.webloja.model.Role;
import com.weboloja.webloja.model.User;
import com.weboloja.webloja.repository.RoleRepository;
import com.weboloja.webloja.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
@RequiredArgsConstructor
public class CadastrarService {

	private final UserRepository userRepository;

    private final RoleRepository roleRepository;

	public ModelAndView cadastrar(User user) {
		String menssagem;
		if(userRepository.findByEmail(user.getEmail()) == null) {
			
			Role role = new Role("ROLE_USER");

	        this.roleRepository.save(role);
			user.setRoles(List.of(role));
	        userRepository.save(user);
	        
	        menssagem = "Cadastro efutuado com sucesso!";
		}else {
			menssagem = "Não foi possivel realizar o cadastro, Email já existente.";
		}

		ModelAndView mv = new ModelAndView("cadastrar");
		mv.addObject("menssagem", menssagem);
		return mv;
	}
}
