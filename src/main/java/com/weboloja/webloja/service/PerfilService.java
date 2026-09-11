package com.weboloja.webloja.service;

import com.weboloja.webloja.model.Endereco;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@Service
public class PerfilService {

    UserService userService;

    EnderecoService enderecoService;

    public ModelAndView getPerfil(){
        ModelAndView mv = new ModelAndView("perfil");
        mv.addObject("user", userService.usuarioLogado());
        Endereco endereco = enderecoService.findID();
        mv.addObject("endereco", endereco);
        return mv;
    }
}
