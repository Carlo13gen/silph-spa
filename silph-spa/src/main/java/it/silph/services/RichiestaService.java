package it.silph.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.silph.model.Richiesta;
import it.silph.repository.RichiestaRepository;

@Service
public class RichiestaService {
	
	@Autowired
	private RichiestaRepository richiestaRepository;
	
	@Transactional
	public Richiesta inserisciRichiesta(Richiesta richiesta) {
		return (Richiesta) richiestaRepository.save(richiesta);
	}
	//ritorna tutte le richieste gestite
	@Transactional
	public List<Richiesta> getAllRequestsdHandled() {
		return (List<Richiesta>) this.richiestaRepository.findAllByGestita(true);
	}
	
	//ritorna tutte le richieste non gestite
	@Transactional
	public List<Richiesta> getAllRequestsdUnHandled() {
		return (List<Richiesta>) this.richiestaRepository.findAllByGestita(false);
	}
	
	@Transactional
	public List<Richiesta> getAllRequests() {
		return (List<Richiesta>) this.richiestaRepository.findAll();
	}
	


	@Transactional
	public Richiesta getRichiestaById(Long id) {
		return this.richiestaRepository.findById(id).get();
	}
	
}
