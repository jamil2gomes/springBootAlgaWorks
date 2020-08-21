package br.com.cursoSpring.app.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.cursoSpring.app.model.Cliente;
import br.com.cursoSpring.app.repository.ClienteRepository;
import br.com.cursoSpring.app.services.ClienteService;

@RestController
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("/clientes")
	public List<Cliente> buscarTodos()
	{
		return this.clienteRepository.findAll();
	}
	
	@GetMapping("/clientes/{id}")
	public ResponseEntity<Cliente> buscarPor(@PathVariable Long id) {
		Optional<Cliente> cliente = this.clienteRepository.findById(id);
		
		if(!cliente.isPresent())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(cliente.get());
	}
	
	@PostMapping("/clientes")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente salvar(@Valid @RequestBody Cliente cliente)
	{
		return this.clienteService.salvar(cliente);
	}
	
	@PutMapping("/clientes/{id}")
	public ResponseEntity<Cliente> atualizar (@PathVariable Long id, @RequestBody Cliente cliente)
	{
		if(!this.clienteRepository.existsById(id))
			return ResponseEntity.notFound().build();
		
		cliente.setId(id);
		cliente = this.clienteService.salvar(cliente);
		return ResponseEntity.ok(cliente);
			
	}
	
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<Void> remover (@PathVariable Long id)
	{
		if(!this.clienteRepository.existsById(id))
			return ResponseEntity.notFound().build();
		
		this.clienteService.excluir(id);
		
		return ResponseEntity.noContent().build();
			
	}
	
}	
	
	