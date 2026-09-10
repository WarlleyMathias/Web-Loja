package com.weboloja.webloja.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Setter
@Entity
@Table(name="TB_PRODUTO")
public class Produto {
	
	@Getter
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	
	@Getter
    @NotBlank
	private String nome;
	
	@Getter
    @NotBlank
	private String imagem;
	
	@NotBlank
	private Float valor;
	
	@NotBlank
	private String descricao;
	
	@Getter
    @NotBlank
	private int quantidade;
	
	@NotBlank
	private String categoria;
	
	public String getValorReal() {
		String novoValor = valor.toString().replace(".", ",");
		if(!novoValor.contains(","))
			novoValor = novoValor+",00";
		else if(novoValor.substring(novoValor.indexOf(",")).length() < 3)
			novoValor = novoValor+"0";
		return novoValor;
	}


}
