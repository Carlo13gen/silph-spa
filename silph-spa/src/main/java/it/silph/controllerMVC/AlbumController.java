package it.silph.controllerMVC;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.silph.model.Album;
import it.silph.services.AlbumService;

@Controller
public class AlbumController {
	
	@Autowired
	private AlbumService albumService;
	
	//ritorna tutti gli album
	@GetMapping("/albums")
	public String albums(Model model) {
		model.addAttribute("albums", this.albumService.getAllAlbum());
		return "albums.html";
	}
	
	//ritorna tutti gli album di un fotografo
	@GetMapping("/fotografo/{id}/albums")
	public String albumsPerFotografo(Model model,@PathVariable("id") Long fotografo_id) {
		model.addAttribute("albums", this.albumService.getAlbumByFotografo(fotografo_id));
		return "albums.html";
	}
	
	@RequestMapping("/nuovoAlbum")
	public String nuovoAlbum(Model model) {
		model.addAttribute("album", new Album());
		return "nuovoAlbum.html";
	}
	
	@PostMapping("/inserisciAlbum")
	public String inserisciAlbum(@Validated @ModelAttribute("album")Album album,
			Model model) throws IOException {
		
		model.addAttribute("inserito", true); //inserisci su thymeleaf
		this.albumService.inserisci(album);
		return "operazioni.html";
	}
	
	@RequestMapping("/scegliAlbum")
	public String scegliAlbum(Model model) {
		model.addAttribute("fotografi", this.albumService.getAllAlbum());
		return "selezionaAlbum.html";
	}
}
