package com.example.kardex.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.kardex.entidad.Kardex;
import com.example.kardex.repositorio.KardexJPA;

@Service
public class KardexService {

	@Autowired
	private KardexJPA jpa;

	@Transactional(readOnly = true)
	public List<Kardex> listar() {
		List<Kardex> list = new ArrayList<>();
	    jpa.findAll().forEach(list::add);
		return list;
	}

	@Transactional(readOnly = true)
	public List<Kardex> listarIdProducto(@PathVariable int id) {
		List<Kardex> list = jpa.findByIdProducto(id);        
		return list;
	}

	@Transactional(readOnly = true)
	public Kardex obtenerPorId(int id) {
		
		Optional<Kardex> item = jpa.findById(id);
	    if (!item.isPresent()) {            
	        return null;
	    }
		return item.get();
	}
	
	
}