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

import it.silph.validator.FotografoValidator;
import it.silph.model.Album;
import it.silph.model.Fotografo;
import it.silph.services.FotografoService;

@Controller
public class FotografoController {



	@Autowired
	private FotografoService fotografoService;

	@Autowired
	private FotografoValidator fotografoValidator;




	@GetMapping("/fotografi")
	public String fotografi(Model model) {
		model.addAttribute("fotografi", this.fotografoService.getAllFotografi());
		return "fotografi.html";
	}


	@RequestMapping("/nuovoFotografo")
	public String nuovoAlbum(Model model) {
		model.addAttribute("album", new Album());
		return "nuovoFotografo.html";
	}


	@PostMapping("/inserisciFotografo")
	public String inserisciFotografo(@Validated @ModelAttribute("fotografo") Fotografo fotografo,
			@RequestParam("image") MultipartFile img,Model model,BindingResult bindingResult) throws IOException {

		this.fotografoValidator.validate(fotografo, bindingResult);

		if(!bindingResult.hasErrors()) {
			byte[] imageData= img.getBytes();

			fotografo.setImmagine(imageData);

			this.fotografoService.inserisci(fotografo);
			model.addAttribute("inserito", true); //inserire su thymeleaf il controllo a questa variabile come fatto su loginPage
			return "operazioni.html";  
		}
		else return "nuovaImmagine.html";
	}
	
	//ritorna la foto di un fotografo
	@GetMapping(value="/fotografoImage/{id}",produces= {MediaType.IMAGE_PNG_VALUE,MediaType.IMAGE_JPEG_VALUE})
	public  @ResponseBody byte[] showImage(@PathVariable("id") Long id) throws IOException{

		Fotografo f = fotografoService.fotografoPerId(id);        
		return f.getImmagine();
	}

}

