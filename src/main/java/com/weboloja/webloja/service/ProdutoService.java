package com.weboloja.webloja.service;

import com.weboloja.webloja.config.FileUploadUtil;
import com.weboloja.webloja.model.Categoria;
import com.weboloja.webloja.model.Produto;
import com.weboloja.webloja.repository.ProdutoRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@Service
public class ProdutoService {

	ProdutoRepository produtoRepository;

	CategoriaService categoriaService;

	UserService userService;
	
	public List<Produto> findAll() {
		return produtoRepository.findAll();
	}
	
	public Produto findID(Long id) {
		return produtoRepository.findById(id).orElse(new Produto());
	}

	public ModelAndView findProdutos(Long id){
		ModelAndView mv = new ModelAndView("produto");
		Produto produto = findID(id);
		List<Integer> itens = new ArrayList<>(produto.getQuantidade());
		for(int i=0; i < produto.getQuantidade(); i++)
			itens.add(i, i+1);
		mv.addObject("user", userService.usuarioLogado());
		mv.addObject("produto", produto);
		mv.addObject("itens", itens);
		return mv;
	}
	
	public List<Produto> findCategoria(String categoria) {
		return produtoRepository.findProdutoByCategoria(categoria);
		
	}
	public ModelAndView getCategoriaProduto(){
		ModelAndView mv = new ModelAndView("addproduto");
		List <Categoria> categorias = categoriaService.findAll();
		mv.addObject("categorias", categorias);
		return mv;
	}
	
	public String save(Produto produto, MultipartFile multipartFile) throws IOException {

		String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));

		// 3. Preenche a propriedade String com o nome da imagem
		produto.setImagem(fileName);

		Produto produtoSalvo = produtoRepository.save(produto);

		String uploadDir = "user-photos/" + produtoSalvo.getId();

		FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

		return "redirect:/addproduto";
	}

}
