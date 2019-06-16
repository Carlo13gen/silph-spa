package it.silph.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.silph.model.Foto;
import it.silph.repository.FotoRepository;

@Service
public class FotoService {
	
	@Autowired
	private FotoRepository fotoRepository;
	
	@Transactional
	public List<Foto> getFotoByAlbumid(Long album_id){
		return (List<Foto>) fotoRepository.findByAlbum_id(album_id);
	}
	
}
