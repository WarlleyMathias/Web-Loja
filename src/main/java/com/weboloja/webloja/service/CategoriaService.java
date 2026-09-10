package com.weboloja.webloja.service;

import com.weboloja.webloja.model.Categoria;
import com.weboloja.webloja.repository.CategoriaRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;



@org.springframework.stereotype.Service
public class CategoriaService {
	
	@Autowired
	CategoriaRepository cr;
	
	public List<Categoria> findAll() {
		return cr.findAll();		
	}
	
	public Categoria findID(Long id) {
		return cr.findById(id).get();
	}
	
	public Categoria save(Categoria categoria) {
		return cr.save(categoria);
	}

}
