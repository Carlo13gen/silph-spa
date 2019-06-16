package it.silph.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.silph.model.Album;

public interface AlbumRepository extends CrudRepository<Album, Long>{
	
	public List<Album> findByFotografo_id(Long fotografo_id);
}
