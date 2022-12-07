package com.example.kardex.repositorio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.kardex.entidad.FacturaDet;

public interface FacturaDetJPA extends CrudRepository<FacturaDet, Integer> {
	List<FacturaDet> findByIdFactura(Integer idFactura);
}
