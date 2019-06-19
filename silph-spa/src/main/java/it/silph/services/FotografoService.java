package it.silph.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.silph.model.Fotografo;
import it.silph.repository.FotografoRepository;

@Service
public class FotografoService {
	
	@Autowired
	private FotografoRepository fotografoRepository;
	
	@Transactional
	public List<Fotografo> getFotografiByNome(String nome){
		return (List<Fotografo>) fotografoRepository.findByNome(nome);
	}

	@Transactional
	public List<Fotografo> getAllFotografi() {
		return (List<Fotografo>) fotografoRepository.findAll();
	}
	
	@Transactional
	public Fotografo inserisci(Fotografo f) {
		return this.fotografoRepository.save(f);
	}
	
	@Transactional
	public Fotografo fotografoPerId(Long id) {
		return this.fotografoRepository.findById(id).get();
	}
}
