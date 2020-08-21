package br.com.cursoSpring.app.services;

import java.util.List;
import java.util.Optional;

import br.com.cursoSpring.app.model.OrdemPedido;

public interface OrdemPedidoServico {
	
	OrdemPedido salvar(OrdemPedido ordemPedido);
	
	Optional<OrdemPedido> buscarPor(Long id);
	
	List<OrdemPedido> buscarTodos();

}
