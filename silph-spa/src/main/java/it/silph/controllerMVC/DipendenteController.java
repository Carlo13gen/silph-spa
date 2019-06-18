package it.silph.controllerMVC;

import javax.servlet.http.HttpSession;
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
import it.silph.validator.LoginFormValidator;

@Controller
public class DipendenteController {
	
	@Autowired
	private AuthController authcontroller;
	
	@Autowired
	private LoginFormValidator loginFormValidator;
	

	
	@RequestMapping(value="/signIn", method = RequestMethod.POST)
	public String loginDipendente(HttpSession session,@Valid @ModelAttribute("auth") Auth auth,Model model, BindingResult bindingResult) {
		this.loginFormValidator.validate(auth, bindingResult);
		if(!bindingResult.hasErrors()) {
			if(authcontroller.IsSignInGranted(auth)) {
				//session.setAttribute("Username", auth.getUsername());
				return "dipendente.html";
				}
			else
				bindingResult.reject("erroreAuth");
		}
		return "loginPage.html";
	}
}

		
	
