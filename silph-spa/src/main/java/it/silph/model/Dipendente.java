package it.silph.model;

import javax.persistence.*;

@Entity
public class Dipendente {

	@Id
	@Column(name="username")
	private String username;
	
	@Column(name="password",nullable=false)
	private String password;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String cognome;
	
	@Column(nullable=false)
	private String role;
	
	//COSTRUTTORE
	public Dipendente() {
	}
	
	public Dipendente(String username, String password, String nome, String cognome, String role) {
		super();
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.role = role;
	}

	//GETTERS & SETTERS
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}
	
	
}