package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.models.Funcionario;

@Repository
public class FuncionarioDAO {


	@PersistenceContext
	private EntityManager manager;
	
	
	public Funcionario find(Long id) {
		return manager.find(Funcionario.class, id);
	}
	

	public List<Funcionario> list() {
		return manager.createQuery("select distinct(f) from Funcionario f" 
				, Funcionario.class).getResultList();
	}
	
	
}
