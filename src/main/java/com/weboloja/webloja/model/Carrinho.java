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
@Getter
@Entity
@Table(name="TB_Carrinho")
public class Carrinho {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private Long idUsuario;
	
	@NotBlank
	private Long idProduto;
	
	@NotBlank
	private int quantidade;

}
