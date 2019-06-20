package it.silph.model;

import java.util.List;

import javax.persistence.*;

@Entity
public class Foto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	private String descrizione;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Tag> tags;
	
	
	@Lob
    @Column(name="immagine",nullable=false)
    private byte[] immagine;
	
	@ManyToOne
	private Album album;
	
	//COSTRUTTORE
	public Foto() {
	}
	
	
	
	public Foto(String nome, String descrizione, List<Tag> tags, byte[] immagine, Album album) {
		super();
		this.nome = nome;
		this.descrizione = descrizione;
		this.tags = tags;
		this.immagine = immagine;
		this.album = album;
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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public byte[] getImmagine() {
		return immagine;
	}

	public void setImmagine(byte[] immagine) {
		this.immagine = immagine;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}
	
	
	
}
