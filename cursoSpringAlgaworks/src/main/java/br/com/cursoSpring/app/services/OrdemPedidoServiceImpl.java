package br.com.cursoSpring.app.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cursoSpring.app.exceptions.NegocioException;
import br.com.cursoSpring.app.model.Cliente;
import br.com.cursoSpring.app.model.OrdemPedido;
import br.com.cursoSpring.app.model.StatusPedido;
import br.com.cursoSpring.app.repository.ClienteRepository;
import br.com.cursoSpring.app.repository.OrdemPedidoRepository;

@Service
public class OrdemPedidoServiceImpl implements OrdemPedidoServico {
	
	@Autowired
	private OrdemPedidoRepository ordemPedidoRepository;
	
	@Autowired
	private ClienteRepository clientRepository;

	@Override
	public OrdemPedido salvar(OrdemPedido ordemPedido) {
		
		Cliente cliente = this.clientRepository.findById(ordemPedido.getCliente().getId())
				.orElseThrow(() -> new NegocioException("Cliente não existe"));
		
		ordemPedido.setCliente(cliente);
		ordemPedido.setStatusPedido(StatusPedido.ABERTA);
		ordemPedido.setDataAbertura(LocalDateTime.now());
		
		return this.ordemPedidoRepository.save(ordemPedido);
	}

	@Override
	public Optional<OrdemPedido> buscarPor(Long id) {	
		return this.ordemPedidoRepository.findById(id);
	}

	@Override
	public List<OrdemPedido> buscarTodos() {
		return this.ordemPedidoRepository.findAll();
	}
	
	
	
	
	

}
