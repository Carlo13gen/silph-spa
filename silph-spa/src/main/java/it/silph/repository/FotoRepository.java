package it.silph.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.silph.model.Foto;

public interface FotoRepository extends CrudRepository<Foto, Long> {
	
	public List<Foto> findByAlbum_id(Long album_id);
}
