package com.weboloja.webloja.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="TB_PRODUTO")
public class Produto {
	
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String imagem;
	
	@NotBlank
	private Float valor;
	
	@NotBlank
	private String descricao;
	
	@NotBlank
	private int quantidade;
	
	@NotBlank
	private String categoria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public float getValor() {
		return valor;
	}
	
	public String getValorReal() {
		String newValor = valor.toString().replace(".", ",");
		if(newValor.indexOf(",") == -1)
			newValor = newValor+",00";
		else if(newValor.substring(newValor.indexOf(",")).length() < 3)
			newValor = newValor+"0";
		newValor.indexOf(",");
		return newValor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Transient
    public String getImagePath() {
        if (imagem == null || id == null) return null;
         
        return "/user-photos/" + id + "/" + imagem;
    }

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	
}
