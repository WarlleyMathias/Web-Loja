package com.weboloja.webloja.service;

import com.weboloja.webloja.model.Categoria;
import com.weboloja.webloja.model.Produto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProdutosService {
    CategoriaService categoriaService;

    ProdutoService produtoService;

    public ModelAndView getProdutos(String categoriaAtual,int pagina){
        ModelAndView mv = new ModelAndView("produtos");

        List<Produto> produtos;

        if(categoriaAtual.equals("Todos"))
            produtos = produtoService.findAll();
        else
            produtos =produtoService.findCategoria(categoriaAtual);

        int pag = produtos.size() / 18;

        if(produtos.size() % 18 != 0)
            pag++;

        List <Integer> paginas = new ArrayList<>(Collections.nCopies(pag,0));
        List <Categoria> categorias = categoriaService.findAll();

        mv.addObject("categorias", categorias);
        mv.addObject("categoriaAtual", categoriaAtual);
        mv.addObject("pagina", pagina);
        mv.addObject("paginas", paginas);
        mv.addObject("produtos", produtos);
        return mv;
    }

    public String addCategoria(Categoria categoria){
        categoriaService.save(categoria);
        return "redirect:/produtos/Todos/1";
    }
}
