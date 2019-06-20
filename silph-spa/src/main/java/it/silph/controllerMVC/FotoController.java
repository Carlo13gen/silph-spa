package it.silph.controllerMVC;



import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import it.silph.model.Foto;
import it.silph.model.Tag;
import it.silph.services.AlbumService;
import it.silph.services.FotoService;
import it.silph.services.TagService;
import it.silph.validator.FotoValidator;

@Controller
public class FotoController {

	@Autowired
	private FotoService fotoService;

	@Autowired
	private TagService tagService;


	@Autowired
	private FotoValidator fotoValidator;

	@Autowired
	private AlbumService albumService;

	@RequestMapping("nuovaImmagine/album")
	public String errore(Model model) {
		model.addAttribute("albums", this.albumService.getAllAlbum());
		return "selezionaAlbum.html";
	}

	@RequestMapping("/nuovaImmagine/album/{id}")
	public String nuovaImmagine(@PathVariable("id") Long id,Model model) {
		model.addAttribute("album_id", id);
		model.addAttribute("immagine", new Foto());
		return "nuovaImmagine.html";
	}

	@PostMapping("/inserisciImmagine")
	public String inserisciFoto(@Validated @ModelAttribute("immagine") Foto foto,
			@RequestParam("image") MultipartFile img,@RequestParam("tags") String tags,
			Model model,BindingResult bindingResult) throws IOException {

		this.fotoValidator.validate(foto, bindingResult);
		String[] t=tags.split(",");


		if(!bindingResult.hasErrors()) {

			byte[] imageData= img.getBytes();

			foto.setImmagine(imageData);

			this.fotoService.inserisciFoto(foto);
			for(String s:t) {
				Tag tag=new Tag(s);
				tag.inserisciFoto(foto);
				this.tagService.inserisci(tag);
			}
			model.addAttribute("inserito", true); 
			return "nuovaImmagine.html";    

		}
		else return "nuovaImmagine.html";
	}

	@GetMapping(value="/imageDisplay/{id}",produces= {MediaType.IMAGE_PNG_VALUE,MediaType.IMAGE_JPEG_VALUE})
	public  @ResponseBody byte[] showImage(@PathVariable("id") Long id) throws IOException{

		Foto foto = fotoService.fotoPerId(id);        
		return foto.getImmagine();
	}


	//ritorna tutte le foto in un album
	@GetMapping("/album/{id}/fotografie")
	public String immaginiPerAlbum(Model model,@PathVariable("id") Long album_id) {
		List<Foto> immagini = this.fotoService.getFotoByAlbumid(album_id);
		if(immagini.isEmpty()) {
			model.addAttribute("vuoto", true);
			return "fotografie.html";
		}
		else {
			model.addAttribute("immagini", immagini);
			return "fotografie.html";
		}
	}

	@GetMapping("/cercaFoto")
	public String cercaFoto(Model model,@RequestParam("ricerca")String ricerca) {
		List<Foto> immagini = this.fotoService.getFotoPerTag(ricerca);
		if(immagini.isEmpty()) {
			model.addAttribute("vuoto", true);
			return "fotografie.html";
		}
		else {
			model.addAttribute("immagini", immagini);
			return "fotografie.html";
		}
	}
}
