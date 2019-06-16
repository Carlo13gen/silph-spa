package it.silph.model;


import javax.persistence.*;

@Entity
public class Tag {
	
	@Id
	private String nome;
	
	//COSTRUTTORE
	public Tag() {
	}

	//GETTERS & SETTERS
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
}