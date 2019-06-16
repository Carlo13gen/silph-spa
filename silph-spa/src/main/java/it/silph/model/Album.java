package it.silph.model;

import java.util.List;

import javax.persistence.*;

@Entity
public class Album {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	private String descrizione;
	
	@ManyToMany
	private List<Tag> tags;
	
	@Lob
    @Column(name="immagine", length=100000,nullable=false)
    private byte[] immagine;
	
	@ManyToOne
	private Fotografo fotografo;
	
	@OneToMany(mappedBy = "album")
	private List<Foto> fotografie;

	//COSTRUTTORE
	public Album() {
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

	public Fotografo getFotografo() {
		return fotografo;
	}

	public void setFotografo(Fotografo fotografo) {
		this.fotografo = fotografo;
	}

	public List<Foto> getFotografie() {
		return fotografie;
	}

	public void setFotografie(List<Foto> fotografie) {
		this.fotografie = fotografie;
	}
}