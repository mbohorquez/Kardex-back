package com.example.kardex.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.example.kardex.entidad.Pedido;

public interface PedidoJPA extends CrudRepository<Pedido, Integer> {

}
