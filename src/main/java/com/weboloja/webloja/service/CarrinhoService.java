package com.weboloja.webloja.service;

import com.weboloja.webloja.config.Compra;
import com.weboloja.webloja.model.Carrinho;
import com.weboloja.webloja.model.Endereco;
import com.weboloja.webloja.model.Produto;
import com.weboloja.webloja.model.User;
import com.weboloja.webloja.repository.CarrinhoRepository;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.dom4j.DocumentException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@Service
public class CarrinhoService {

	private final CarrinhoRepository carrinhoRepository;

	private final ProdutoService produtoService;

	private final EnderecoService enderecoService;

	private final UserService userService;

	public String updateCarrinho1(Carrinho carrinho) {
		User usuario = userService.usuarioLogado();
		carrinho.setIdUsuario(usuario.getId());
		if(carrinhoRepository.existsById(carrinho.getId())){
			carrinhoRepository.save(carrinho);
			return "redirect:/produto/" + carrinho.getIdProduto().toString();
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Carrinho não existe.");
	}
	
	public String updateCarrinho2(Carrinho carrinho) {
		User usuario = userService.usuarioLogado();
		carrinho.setIdUsuario(usuario.getId());
		if(carrinhoRepository.existsById(carrinho.getId())){
			carrinhoRepository.save(carrinho);
			return "redirect:/carrinho";
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Carrinho não existe.");
	}
	
	public String dropCarrinho(Long IdUsuario, Long IdProduto) {
		Long id = carrinhoRepository
				.findByIdUsuarioAndIdProduto(IdUsuario,IdProduto).getId();
		carrinhoRepository.deleteById(id);
		return "redirect:/carrinho";
	}

	public ModelAndView getCarrinho (){
		User usuario = userService.usuarioLogado();
		ModelAndView mv = new ModelAndView("carrinho");
		List<Carrinho> carrinhos = carrinhoRepository.findCarrinhoByIdUsuario(usuario.getId());
		List <Produto> produtos = new ArrayList<>();
		for (Carrinho carrinho : carrinhos) {
			produtos.add(produtoService.findID(carrinho.getIdProduto()));
		}
		mv.addObject("produtos", produtos);
		mv.addObject("carrinho", carrinhos);
		return mv;
	}

	public ModelAndView finalizaCompra() {
		User usuario = userService.usuarioLogado();
		ModelAndView mv = getCarrinho();
		@SuppressWarnings("unchecked")
		List <Produto> produtos = (List<Produto>) mv.getModel().get("produtos");
		@SuppressWarnings("unchecked")
		List <Carrinho> carrinho = (List<Carrinho>) mv.getModel().get("carrinho");
		Endereco endereco = enderecoService.findID();
		if (produtos.isEmpty() || endereco.getBairro() == null) {
			if (produtos.isEmpty())
				mv.addObject("menssagem", "Selecione um produto antes de finalizar a compra.");
			else
				mv.addObject("menssagem", "É necessário criar um endereço antes.");
		} else {
			try {
				Compra c = new Compra();
				String codigo = c.comprar(endereco, usuario, carrinho, produtos);
				mv.addObject("codigo", codigo);
			} catch (DocumentException ignored) {
			}
		}
		return mv;
	}

}
