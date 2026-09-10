package com.weboloja.webloja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weboloja.webloja.model.Endereco;
import com.weboloja.webloja.repository.EnderecoRepository;

@Service
public class EnderecoService {
	
	@Autowired
	EnderecoRepository er;
	
	public Endereco save(Endereco endereco) {
		return er.save(endereco);
	}
	
	public Endereco findID(Long id_user) {
		return er.findEndereco(id_user);
	}
	
	public void update(Endereco endereco, Long id_user) {
		er.updateEndereco(endereco.getBairro(), endereco.getCep(), endereco.getCidade(), endereco.getEstado(), endereco.getNumero(), endereco.getRuaAvenida(), endereco.getComplemento(), endereco.getContinente(), endereco.getTelefone(), id_user);
	}

}
