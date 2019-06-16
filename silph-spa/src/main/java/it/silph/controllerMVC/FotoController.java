package it.silph.controllerMVC;



import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.silph.model.Foto;
import it.silph.services.FotoService;
import it.silph.validator.FotoValidator;

@Controller
public class FotoController {

	@Autowired
	private FotoService fotoService;

	@Autowired
	private FotoValidator fotoValidator;

	@RequestMapping("/nuovaImmagine")
	public String nuovaImmagine(Model model) {
		model.addAttribute("immagine", new Foto());
		return "nuovaImmagine.html";
	}



	@PostMapping("/inserisciImmagine")
	public String inserisciFoto(@Validated @ModelAttribute("immagine") Foto foto,
			@RequestParam("image") MultipartFile img,Model model,BindingResult bindingResult) throws IOException {

		this.fotoValidator.validate(foto, bindingResult);

		if(!bindingResult.hasErrors()) {
			byte[] imageData= img.getBytes();

			foto.setImmagine(imageData);

			this.fotoService.inserisciFoto(foto);
			return "immagineInserita.html";  //pagina che conferma l'inserimento;

		}
		else return "nuovaImmagine.html";
	}


	@PostMapping("/confermaInserimento")
	public String confermaImmagine(@ModelAttribute("immagine") Foto foto,Model model) {
		this.fotoService.inserisciFoto(foto);

		return "dipendentePage.html";
	}

}
