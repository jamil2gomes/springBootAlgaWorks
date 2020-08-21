package br.com.cursoSpring.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.cursoSpring.app.dto.OrdemPedidoDTO;
import br.com.cursoSpring.app.model.OrdemPedido;
import br.com.cursoSpring.app.services.OrdemPedidoServico;

@RestController
@RequestMapping("/pedido")
public class OrdemPedidoController {

	@Autowired
	private OrdemPedidoServico ordemPedidoServico;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrdemPedidoDTO salvar(@Valid @RequestBody OrdemPedido ordemPedido) {
		return this.toDto(ordemPedidoServico.salvar(ordemPedido));
	}
	
	@GetMapping
	public List<OrdemPedidoDTO>buscarTodos(){
		return this.toDtoList(ordemPedidoServico.buscarTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrdemPedidoDTO> buscarPor(@PathVariable Long id) {
		Optional<OrdemPedido> ordemPedido = this.ordemPedidoServico.buscarPor(id);

		if (ordemPedido.isPresent()) {
			OrdemPedidoDTO ordem = this.toDto(ordemPedido.get());
			return ResponseEntity.ok().body(ordem);
		}

		return ResponseEntity.notFound().build();
	}

	private OrdemPedidoDTO toDto(OrdemPedido ordemPedido) {
		return this.modelMapper.map(ordemPedido, OrdemPedidoDTO.class);
	}
	
	private List<OrdemPedidoDTO> toDtoList(List<OrdemPedido> ordemPedidoList)
	{
		return ordemPedidoList.stream().map(ordemPedido->toDto(ordemPedido))
				.collect(Collectors.toList());
	}

}
