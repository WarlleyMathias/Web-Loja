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
@Table(name="TB_ENDERECO")
public class Endereco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String cep;
	
	@NotBlank
	private String estado;
	
	@NotBlank
	private String cidade;
	
	@NotBlank
	private String bairro;
	
	@NotBlank
	private String ruaAvenida;
	
	@NotBlank
	private String complemento;
	
	@NotBlank
	private String continente;
	
	@NotBlank
	private String numero;
	
	@NotBlank
	private String telefone;

	@NotBlank
	private Long idUser;

}
