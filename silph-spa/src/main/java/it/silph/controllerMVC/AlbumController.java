package it.silph.controllerMVC;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import it.silph.model.Album;
import it.silph.model.Fotografo;
import it.silph.services.AlbumService;
import it.silph.services.FotografoService;

@Controller
public class AlbumController {
	
	@Autowired
	private AlbumService albumService;
	
	@Autowired
	private FotografoService fotografoService;
	
	//ritorna tutti gli album di un fotografo
	@GetMapping("/fotografo/{id}/albums")
	public String albumsPerFotografo(Model model,@PathVariable("id") Long fotografo_id) {
		List<Album> albums = this.albumService.getAlbumByFotografo(fotografo_id);
		model.addAttribute("albums", albums);
		model.addAttribute("vuoto", albums.isEmpty());
		return "albums.html";
	}
	
	@RequestMapping("/nuovoAlbum/fotografo/")
	public String errore(Model model) {
		model.addAttribute("fotografi", this.fotografoService.getAllFotografi());
		return "selezionaFotografo.html";
	}
	@RequestMapping("/nuovoAlbum/fotografo/{id}")
	public String nuovoAlbum(@PathVariable("id") Long id, Model model) {
		Album a =  new Album();
		Fotografo f = this.fotografoService.fotografoPerId(id);
		f.getAlbum().add(a);
		a.setFotografo(f);
		model.addAttribute("fotografo_id", id);
		model.addAttribute("album", a);
		return "nuovoAlbum.html";
	}
	
	@PostMapping("/inserisciAlbum")
	public String inserisciAlbum(@ModelAttribute("album") Album album, @RequestParam("image") MultipartFile img,
			Model model) throws IOException {
		
		byte[] imageData = img.getBytes();
		album.setImmagine(imageData);
		
		this.albumService.inserisci(album);
		model.addAttribute("inserito", true); //inserisci su thymeleaf
		return "forward:/operazioni";  
	}
	
	@GetMapping(value="/albumImage/{id}",produces= {MediaType.IMAGE_PNG_VALUE,MediaType.IMAGE_JPEG_VALUE})
	public  @ResponseBody byte[] showImage(@PathVariable("id") Long id) throws IOException{
		
		Album album = albumService.getAlbumPerId(id);        
	    return album.getImmagine();
	}
	
	@RequestMapping("/scegliAlbum")
	public String scegliAlbum(Model model) {
		model.addAttribute("albums", this.albumService.getAllAlbum());
		return "selezionaAlbum.html";
	}
	
}
