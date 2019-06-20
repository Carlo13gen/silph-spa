package it.silph.model;


import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Tag {
	
	@Id
	private String nome;
	
	@ManyToMany(mappedBy="tags")
	private List<Foto> foto;
	
	


	//COSTRUTTORE
	public Tag() {
	}
	

	public Tag(String nome) {
		super();
		this.nome = nome;
		this.foto=new LinkedList<>();
	}
	
	public void inserisciFoto(Foto f) {
		this.foto.add(f);
	}


	//GETTERS & SETTERS
	
	public List<Foto> getFoto() {
		return foto;
	}


	public void setFoto(List<Foto> foto) {
		this.foto = foto;
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
}