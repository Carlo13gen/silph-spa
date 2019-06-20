package it.silph.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.silph.model.Tag;
import it.silph.repository.TagRepository;

@Service
public class TagService {
	
	@Autowired
	private TagRepository tagRepository;
	
	@Transactional
	public void inserisci(Tag t) {
		this.tagRepository.save(t);
	}
	
}
