package it.silph.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.silph.model.Album;
import it.silph.repository.AlbumRepository;

@Service
public class AlbumService {
	
	@Autowired
	private AlbumRepository albumRepository;
	
	@Transactional
	public List<Album> getAlbumByFotografo(Long fotografo_id) {
		return (List<Album>) this.albumRepository.findByFotografo_id(fotografo_id);
	}
	
	@Transactional
	public List<Album> getAllAlbum() {
		return (List<Album>) this.albumRepository.findAll();
	}
	
	@Transactional
	public Album inserisci(Album a) {
		return this.albumRepository.save(a);
	}
	
	
	
	
}
