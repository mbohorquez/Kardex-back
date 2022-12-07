package com.example.kardex.repositorio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.kardex.entidad.PedidoDet;

public interface PedidoDetJPA extends CrudRepository<PedidoDet, Integer>  {
	List<PedidoDet> findByIdPedido(Integer idPedido);
}
