package it.silph.model;

import java.util.LinkedList;
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
	
	
	@Lob
    @Column(name="immagine", length=100000)
    private byte[] immagine;
	
	@ManyToOne
	private Fotografo fotografo;
	
	@OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
	private List<Foto> fotografie;

	//COSTRUTTORE
	public Album() {
	}
	
	

	public Album(String nome, String descrizione, byte[] immagine, Fotografo fotografo) {
		super();
		this.nome = nome;
		this.descrizione = descrizione;
		this.immagine = immagine;
		this.fotografo = fotografo;
		this.fotografie = new LinkedList<>();
	}
	
	public void inserisciFoto(Foto foto) {
		this.fotografie.add(foto);
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