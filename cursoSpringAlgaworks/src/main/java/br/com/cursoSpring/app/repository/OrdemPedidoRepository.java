package br.com.cursoSpring.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cursoSpring.app.model.OrdemPedido;

@Repository
public interface OrdemPedidoRepository extends JpaRepository<OrdemPedido, Long> {

}
