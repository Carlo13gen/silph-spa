package it.silph.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import it.silph.model.Foto;

@Repository
public interface FotoRepository extends CrudRepository<Foto, Long> {
	
	public List<Foto> findByAlbum_id(Long album_id);

	public List<Foto> findAllByTags_nome(String tag);
	
}
