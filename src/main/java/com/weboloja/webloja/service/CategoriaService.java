package com.weboloja.webloja.service;

import com.weboloja.webloja.model.Categoria;
import com.weboloja.webloja.repository.CategoriaRepository;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoriaService {

	private final CategoriaRepository categoriaRepository;
	
	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}
	
	public Categoria save(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

}
