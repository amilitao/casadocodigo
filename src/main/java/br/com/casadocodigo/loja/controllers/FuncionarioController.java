package br.com.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.casadocodigo.loja.daos.FuncionarioDAO;
import br.com.casadocodigo.loja.models.Agenda;
import br.com.casadocodigo.loja.models.Funcionario;


@Transactional
@Controller
@RequestMapping("/funcionario")
public class FuncionarioController {
		
	@Autowired
	private FuncionarioDAO funcionarioDAO;	
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String cadastro() {
		
		System.out.println("testando");		
		
		Funcionario funcionario = funcionarioDAO.find(2L);
		
		System.out.println(funcionario.getId());
		System.out.println(funcionario.getNome());
		
		for(Agenda agenda : funcionario.getAgendamento()) {
			System.out.println(agenda.getPeriodo());
		}
		
		
		return "hello-word";
		
	}

}
