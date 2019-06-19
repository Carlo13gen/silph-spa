package it.silph.model;

import javax.persistence.*;

@Entity
public class Richiesta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(nullable = false)
	private String nomeCliente;
	
	@Column(nullable = false)
	private String cognomeCliente;
	
	@Column(nullable = false)
	private String emailCliente;
	
	@Column(columnDefinition = "boolean default false")
	private boolean gestita;
	

	//Abbiamo detto che gli id delle foto vengono inseriti come stringa
	//Ogni id separato da una virgola
	@Column(nullable = false)
	private String foto;
	
	//COSTRUTTORE
	public Richiesta() {
	}

	//GETTERS & SETTERS
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getCognomeCliente() {
		return cognomeCliente;
	}

	public void setCognomeCliente(String cognomeCliente) {
		this.cognomeCliente = cognomeCliente;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}
	
	public boolean isGestita() {
		return gestita;
	}

	public void setGestita(boolean gestita) {
		this.gestita = gestita;
	}
	
	
}
