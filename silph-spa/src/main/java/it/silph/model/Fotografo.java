package it.silph.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Fotografo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String cognome;
	
	@Lob
    @Column(name="immagine", length=100000,nullable=false)
    private byte[] immagine;
	
	@OneToMany(mappedBy = "fotografo", cascade = CascadeType.ALL)
	private List<Album> album;

	
	//COSTRUTTORE
	public Fotografo() {
	}

	
	public Fotografo(String nome, String cognome, byte[] immagine) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.immagine = immagine;
		this.album = new LinkedList<>();
	}


	//GETTERS & SETTERS
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public byte[] getImmagine() {
		return immagine;
	}

	public void setImmagine(byte[] immagine) {
		this.immagine = immagine;
	}

	public List<Album> getAlbum() {
		return album;
	}

	public void setAlbum(List<Album> album) {
		this.album = album;
	}

}
