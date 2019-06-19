package it.silph.controllerMVC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.silph.model.Richiesta;
import it.silph.services.RichiestaService;
import it.silph.validator.RichiestaValidator;

@Controller
public class RichiestaController {

	@Autowired
	private RichiestaService richiestaService;
	
	@Autowired
	private RichiestaValidator richiestaValidator;
	
	
	@RequestMapping("/creaRichiesta")
	private String creaRichiesta(Model model) {
		model.addAttribute("richiesta", new Richiesta());
		return "richiestaForm.html";
	}
	
	@PostMapping("/inserisciRichiesta")
	public String inserisciRichiesta(@Validated @ModelAttribute("richiesta") Richiesta richiesta, Model model,
			BindingResult bindingResult) {
		this.richiestaValidator.validate(richiesta, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.richiestaService.inserisciRichiesta(richiesta);
			model.addAttribute("richiesta", richiesta);
			model.addAttribute("inserita", true);
			return "dettaglioRichiesta.html";
		}
		else
			return "richiestaForm.html";
	}
	
	@GetMapping("/listaRichieste")
	public String listaRichieste(Model model) {
		model.addAttribute("richieste", this.richiestaService.getAllRequest());
		return "listaRichieste.html";
	}
	
	@GetMapping("/richiesta/{id}")
	public String richiestaById(@PathVariable("id") Long id, Model model) {
		if(id!=null) {
			model.addAttribute("richiesta", this.richiestaService.getRichiestaById(id));
			return "dettaglioRichiesta.html";
		}
		else 
			return "listaRichieste.html";
	}
}
