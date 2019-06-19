package it.silph.controllerMVC;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.silph.services.AlbumService;
import it.silph.services.FotoService;
import it.silph.services.FotografoService;

@Controller
public class MainController {

	@Autowired
	private AlbumService albumService;
	
	@Autowired
	private FotoService fotoService;

	@Autowired
	private FotografoService fotografoService;

	public MainController() {
        super();
    }
	
	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {
        return "home";
    }
	
	@GetMapping("/foto")
	public String immagini(Model model) {
		model.addAttribute("immagini", this.fotoService.allFoto());
		return "fotografie.html";
	}


	@GetMapping("/fotografi")
	public String fotografi(Model model) {
		model.addAttribute("fotografi", this.fotografoService.getAllFotografi());
		return "fotografi.html";
	}
	
	//ritorna tutti gli album
	@GetMapping("/albums")
	public String albums(Model model) {
		model.addAttribute("albums", this.albumService.getAllAlbum());
		return "albums.html";
	}
	
	
	
}