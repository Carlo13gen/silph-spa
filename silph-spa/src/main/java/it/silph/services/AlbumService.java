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
		return (List<Album>) albumRepository.findByFotografo_id(fotografo_id);
	}
	
	
}
