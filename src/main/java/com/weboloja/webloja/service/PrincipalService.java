package com.weboloja.webloja.service;

import com.weboloja.webloja.model.Produto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class PrincipalService {

    ProdutoService produtoService;

    public ModelAndView getPrincipal(){
        ModelAndView mv = new ModelAndView("principal");
        List<Produto> produtos = produtoService.findAll();
        List <Produto> produtos1 = new ArrayList<Produto>();
        List <Produto> produtos2 = new ArrayList<Produto>();
        Random random = new Random();
        for(int i = 0; i < 6 && !produtos.isEmpty(); i++) {
            int r1 = random.nextInt(produtos.size());
            produtos1.add(produtos.get(r1));
            produtos.remove(r1);
        }
        for(int i = 0; i < 6 && !produtos.isEmpty(); i++) {
            int r2 = random.nextInt(produtos.size());
            produtos2.add(produtos.get(r2));
            produtos.remove(r2);
        }
        mv.addObject("produtos1", produtos1);
        mv.addObject("produtos2", produtos2);
        return mv;
    }
}
