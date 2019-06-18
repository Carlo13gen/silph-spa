package it.silph.controllerMVC;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import it.silph.model.dipendente.Auth;



@Controller
public class MainController {

	
	@RequestMapping("/login")
	public String pagina(Model model) {
		model.addAttribute("auth", new Auth());
		return "loginPage.html";
	}
	
	@RequestMapping("/fotografi")
	public String fotografiPage() {
		return "fotografiPage.html";
	}
	
	@RequestMapping("/home")
	public String homePage() {
		return "index.html";
	}
	
	
}