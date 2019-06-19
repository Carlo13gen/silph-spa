package it.silph.controllerMVC;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DipendenteController {


	@GetMapping("/login")
	public String login(Model model) {
		return "loginPage.html";
	}
	
	@RequestMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "loginPage.html";
	}
	
	@RequestMapping("/logout")
	public String logout(Model model) {
		return "loginPage.html";
	}

	@RequestMapping("/operazioni")
	public String dipendente(Model model) {
		UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String role = details.getAuthorities().iterator().next().getAuthority();     // get first authority
		model.addAttribute("username", details.getUsername());
		model.addAttribute("role", role);
		return "operazioniDipendente.html";
	}
	

}



