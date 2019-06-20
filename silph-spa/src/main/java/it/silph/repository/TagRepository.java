package it.silph.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.silph.model.Foto;
import it.silph.model.Tag;

public interface TagRepository extends CrudRepository<Tag,String> {

	public List<Foto> findAllByNome(String tag);
	
}
