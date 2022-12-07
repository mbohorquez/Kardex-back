package com.example.kardex.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.kardex.entidad.PedidoDet;
import com.example.kardex.repositorio.PedidoDetJPA;

@Service
public class PedidoDetService {

	@Autowired
	private PedidoDetJPA jpa;
    
    @Transactional(readOnly = true)
	public List<PedidoDet> listar() {
		List<PedidoDet> list = new ArrayList<>();
        jpa.findAll().forEach(list::add);
		return list;
	}
    
    @Transactional(readOnly = true)
	public List<PedidoDet> listarIdPedido(@PathVariable int id) {
		List<PedidoDet> list = jpa.findByIdPedido(id);        
		return list;
	}

    @Transactional(readOnly = true)
	public PedidoDet obtenerPorId(int id) {
		
		Optional<PedidoDet> item = jpa.findById(id);
        if (!item.isPresent()) {            
            return null;
        }
		return item.get();
	}
    @Transactional(readOnly = false)
	public PedidoDet agregar(PedidoDet entidad) {
		Optional<PedidoDet> item = jpa.findById(entidad.getSecuencia());

        if (item.isPresent()) {            
            throw new RuntimeException("Error Llave Primaria");
        }
		return jpa.save(entidad);
	}
    @Transactional(readOnly = false)
	public PedidoDet editar(PedidoDet entidad) {
		Optional<PedidoDet> item = jpa.findById(entidad.getSecuencia());

        if (!item.isPresent()) {            
            throw new RuntimeException("PedidoDet no existe");
        }
		return jpa.save(entidad);
	}
	
}
