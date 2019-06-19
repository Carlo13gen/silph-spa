package it.silph.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.silph.model.dipendente.Dipendente;

@Repository
public interface DipendenteRepository extends CrudRepository<Dipendente, String>{
	
	public Dipendente findByUsername(String username);
}