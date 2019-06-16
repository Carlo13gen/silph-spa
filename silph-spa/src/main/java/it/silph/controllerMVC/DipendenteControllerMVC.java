package it.silph.controllerMVC;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.silph.controllerGRASP.AuthController;
import it.silph.model.dipendente.Auth;
import it.silph.validator.AuthValidator;


@Controller
public class DipendenteControllerMVC {

	
	
	@Autowired
	private AuthController authcontroller;
	
	@Autowired
	private AuthValidator authValidator;
	

	
	@RequestMapping(value="/signIn", method = RequestMethod.POST)
	public String loginDipendente(@Valid @ModelAttribute("auth") Auth auth,Model model, BindingResult bindingResult) {
		this.authValidator.validate(auth, bindingResult);
		if(!bindingResult.hasErrors() && authcontroller.IsSignInGranted(auth)) {
			model.addAttribute("auth", auth);
			return "dipendente.html";
		}
		else
			return "loginPage.html";
		
	}
	
	@RequestMapping("/login")
	public String pagina(Model model) {
		model.addAttribute("auth", new Auth());
		return "loginPage.html";
	}
		
	
}