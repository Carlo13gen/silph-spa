package it.silph.controllerGRASP;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import it.silph.model.dipendente.Auth;
import it.silph.model.dipendente.Dipendente;
import it.silph.services.DipendenteService;

@Controller
public class AuthController {
	
	public AuthController() {
		
	}
	@Autowired
	private DipendenteService dipendenteService;
	
	
	public boolean IsSignInGranted(Auth auth) {
		Dipendente dipendenteTrovato = this.dipendenteService.getDipendente(auth.getUsername());
		if(dipendenteTrovato!=null && dipendenteTrovato.checkPassword(auth.getPassword()))
			return true;
		return false;
	}
}
