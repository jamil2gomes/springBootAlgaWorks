package br.com.cursoSpring.app.services;

import br.com.cursoSpring.app.model.Cliente;

public interface ClienteService {
	
	Cliente salvar(Cliente cliente);
	
	void excluir (Long id);
}
