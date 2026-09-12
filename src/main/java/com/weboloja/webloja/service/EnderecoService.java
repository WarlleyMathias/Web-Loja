package com.weboloja.webloja.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.weboloja.webloja.model.Endereco;
import com.weboloja.webloja.repository.EnderecoRepository;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@Service
public class EnderecoService {

	private final EnderecoRepository enderecoRepository;

	private final UserService userService;

	public String save(Endereco endereco) {
		endereco.setIdUser(userService.usuarioLogado().getId());
		enderecoRepository.save(endereco);
		return "redirect:/carrinho";
	}

	public Endereco findID() {
		return enderecoRepository.findEnderecoByIdUser(userService.usuarioLogado().getId());
	}

		public ModelAndView getEditarEndereco() {
		Endereco endereco = enderecoRepository.findEnderecoByIdUser(userService.usuarioLogado().getId());
		if(endereco.getBairro().isEmpty()) {
            return new ModelAndView("criarEndereco");
		}else {
			ModelAndView mv = new ModelAndView("editarEndereco");
			mv.addObject("endereco", endereco);
			return mv;
		}
	}
	
	public String update(Endereco endereco) {
		if (enderecoRepository.existsByIdUser(userService.usuarioLogado().getId())){
			enderecoRepository.save(endereco);
			return "redirect:/perfil";
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Endereço não existe para ser atualizado.");
	}

}
