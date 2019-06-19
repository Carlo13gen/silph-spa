package it.silph.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.silph.model.Richiesta;

public interface RichiestaRepository extends CrudRepository<Richiesta, Long>{
	
	public List<Richiesta> findAllByGestita(boolean bool);
}
