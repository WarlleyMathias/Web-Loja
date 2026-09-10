package com.weboloja.webloja.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.weboloja.webloja.model.Endereco;

@org.springframework.stereotype.Repository
public interface EnderecoRepository extends JpaRepository <Endereco, Long>{
	
	@Query(value ="SELECT * FROM public.tb_endereco WHERE id_user = :id_user", nativeQuery = true)
	Endereco findEndereco(@Param ("id_user") Long id_user);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE public.tb_endereco SET bairro = :bairro, cep = :cep, cidade = :cidade, estado = :estado, "
			+ "numero = :numero, rua_avenida = :rua_avenida, complemento = :complemento, continente = :continente, telefone = :telefone WHERE id_user = :id_user", nativeQuery = true)
	void updateEndereco(@Param("bairro") String bairro, @Param("cep") String cep, @Param("cidade") String cidade, @Param("estado") String estado, 
			@Param("numero") String numero, @Param("rua_avenida") String rua_avenida, @Param("complemento") String complemento, @Param("continente") String continente, @Param("telefone") String telefone, @Param("id_user") Long id_user);

}

