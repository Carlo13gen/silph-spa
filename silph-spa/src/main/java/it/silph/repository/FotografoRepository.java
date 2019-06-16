package it.silph.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.silph.model.Fotografo;

public interface FotografoRepository extends CrudRepository<Fotografo, Long>{
	
	public List<Fotografo> findByNome(String nome);
	public List<Fotografo> findByNomeAndCognome(String nome, String cognome);
	
}
