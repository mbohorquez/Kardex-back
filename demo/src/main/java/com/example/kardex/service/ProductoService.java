package com.example.kardex.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.kardex.entidad.Producto;
import com.example.kardex.repositorio.ProductoJPA;

@Service
public class ProductoService{
	   
    @Autowired
	private ProductoJPA jpa;
    
    @Transactional(readOnly = true)
	public List<Producto> listar() {
		List<Producto> list = new ArrayList<>();
        jpa.findAll().forEach(list::add);
		return list;
	}

    @Transactional(readOnly = true)
	public Producto obtenerPorId(int id) {
		
		Optional<Producto> item = jpa.findById(id);
        if (!item.isPresent()) {            
            return null;
        }
		return item.get();
	}
    @Transactional(readOnly = false)
	public Producto agregar(Producto entidad) {
		Optional<Producto> item = jpa.findById(entidad.getId());

        if (item.isPresent()) {            
            throw new RuntimeException("Error Llave Primaria");
        }
		return jpa.save(entidad);
	}
    @Transactional(readOnly = false)
	public Producto editar(Producto entidad) {
		Optional<Producto> item = jpa.findById(entidad.getId());

        if (!item.isPresent()) {            
            throw new RuntimeException("Producto no existe");
        }
		return jpa.save(entidad);
	}

}
