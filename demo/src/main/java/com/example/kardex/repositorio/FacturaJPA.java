package com.example.kardex.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.example.kardex.entidad.Factura;

public interface FacturaJPA extends CrudRepository<Factura, Integer> {

}
