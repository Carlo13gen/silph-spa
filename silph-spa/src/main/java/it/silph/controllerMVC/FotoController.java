package it.silph.controllerMVC;



import java.io.IOException;

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

import it.silph.model.Album;
import it.silph.model.Foto;
import it.silph.services.AlbumService;
import it.silph.services.FotoService;
import it.silph.validator.FotoValidator;

@Controller
public class FotoController {

	@Autowired
	private FotoService fotoService;
	
	@Autowired
	private AlbumService albumService;

	@Autowired
	private FotoValidator fotoValidator;

	@RequestMapping("/nuovaImmagine/Album{id}")
	public String nuovaImmagine(@PathVariable("id") Long id,Model model) {
		
		Foto foto=new Foto();
		
		Album a=this.albumService.getAlbumPerId(id);
		foto.setAlbum(a);
		
		model.addAttribute("immagine", foto);
		return "nuovaImmagine.html";
	}

	@PostMapping("/inserisciImmagine")
	public String inserisciFoto(@Validated @ModelAttribute("immagine") Foto foto,
			@RequestParam("image") MultipartFile img,
			Model model,BindingResult bindingResult) throws IOException {

		this.fotoValidator.validate(foto, bindingResult);

		if(!bindingResult.hasErrors()) {
			byte[] imageData= img.getBytes();
			
			foto.setImmagine(imageData);
			
			this.fotoService.inserisciFoto(foto);
			model.addAttribute("inserita", true); //aggiungi controllo su thymeleaf
			return "nuovaImmagine.html";  //inserire bottone per terminare inserimento

		}
		else return "nuovaImmagine.html";
	}

	@GetMapping(value="/imageDisplay/{id}",produces= {MediaType.IMAGE_PNG_VALUE,MediaType.IMAGE_JPEG_VALUE})
	public  @ResponseBody byte[] showImage(@PathVariable("id") Long id) throws IOException{
		
		Foto foto = fotoService.fotoPerId(id);        
	    return foto.getImmagine();
	}
	
	
	@GetMapping("/fotografie")
	public String immagini(Model model) {
		model.addAttribute("immagini", this.fotoService.allFoto());
		return "fotografie.html";
	}
	
	//ritorna tutte le foto in un album
	@GetMapping("/album/{id}/fotografie")
	public String immaginiPerAlbum(Model model,@PathVariable("id") Long album_id) {
		model.addAttribute("immagini", this.fotoService.getFotoByAlbumid(album_id));
		return "fotografie.html";
	}
}
