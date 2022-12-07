package com.example.kardex.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.kardex.entidad.FacturaDet;
import com.example.kardex.repositorio.FacturaDetJPA;


@Service
public class FacturaDetService {

	@Autowired
	private FacturaDetJPA jpa;
    
    @Transactional(readOnly = true)
	public List<FacturaDet> listar() {
		List<FacturaDet> list = new ArrayList<>();
        jpa.findAll().forEach(list::add);
		return list;
	}
    
    @Transactional(readOnly = true)
	public List<FacturaDet> listarIdFactura(@PathVariable int id) {
		List<FacturaDet> list = jpa.findByIdFactura(id);        
		return list;
	}

    @Transactional(readOnly = true)
	public FacturaDet obtenerPorId(int id) {
		
		Optional<FacturaDet> item = jpa.findById(id);
        if (!item.isPresent()) {            
            return null;
        }
		return item.get();
	}
    @Transactional(readOnly = false)
	public FacturaDet agregar(FacturaDet entidad) {
		Optional<FacturaDet> item = jpa.findById(entidad.getSecuencia());

        if (item.isPresent()) {            
            throw new RuntimeException("Error Llave Primaria");
        }
		return jpa.save(entidad);
	}
    @Transactional(readOnly = false)
	public FacturaDet editar(FacturaDet entidad) {
		Optional<FacturaDet> item = jpa.findById(entidad.getSecuencia());

        if (!item.isPresent()) {            
            throw new RuntimeException("FacturaDet no existe");
        }
		return jpa.save(entidad);
	}
}
