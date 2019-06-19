package it.silph.controllerMVC;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import it.silph.model.Fotografo;

@Controller
public class FotografoController {
	
	
	@RequestMapping("/nuovoFotografo")
	public String nuovaImmagine(Model model) {
		model.addAttribute("fotografo", new Fotografo());
		return "nuovoFotografo.html";
	}
}
