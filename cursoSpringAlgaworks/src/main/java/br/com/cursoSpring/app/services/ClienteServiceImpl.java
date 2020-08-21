package br.com.cursoSpring.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cursoSpring.app.exceptions.NegocioException;
import br.com.cursoSpring.app.model.Cliente;
import br.com.cursoSpring.app.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService{
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public Cliente salvar(Cliente cliente) {
		Cliente clienteBuscado = this.clienteRepository.findByEmail(cliente.getEmail());
		
		if(clienteBuscado!= null && !clienteBuscado.equals(cliente))
			 throw new NegocioException("Já existe cliente cadastro com esse email!");
			
		return this.clienteRepository.save(cliente);
	}

	@Override
	public void excluir(Long id) {
		this.clienteRepository.deleteById(id);
		
	}
}
