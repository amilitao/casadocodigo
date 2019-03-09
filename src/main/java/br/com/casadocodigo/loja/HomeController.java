package br.com.casadocodigo.loja;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	
	@RequestMapping("/")
	public String index() {
		System.out.println("Carregando produtos");
		return "hello-word";
	}

}
