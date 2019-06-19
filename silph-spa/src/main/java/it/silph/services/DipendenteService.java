package it.silph.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.silph.model.dipendente.Dipendente;
import it.silph.repository.DipendenteRepository;

@Service
public class DipendenteService {

	@Autowired
	private DipendenteRepository dipendenteRepository;
	
	@Transactional
	public Dipendente getDipendente(String username) {
		return (Dipendente) dipendenteRepository.findByUsername(username);
	}
}
