package com.example.kardex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kardex.conversor.ProductoConversor;
import com.example.kardex.modelo.ProductoModelo;
import com.example.kardex.service.ProductoService;

@CrossOrigin
@RequestMapping("Productos")
@RestController
public class ProductoController {
	
	@Autowired
	private ProductoService service;
	@Autowired
	private ProductoConversor conversor;
	
	@GetMapping
	public ResponseEntity<List<ProductoModelo>> listar() {	
		try {
			List<ProductoModelo> productos = conversor.listaEntidadAModelo(service.listar());
			return new ResponseEntity<List<ProductoModelo>>(productos, HttpStatus.OK);	
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@PostMapping
	public ResponseEntity<ProductoModelo> agregar(@RequestBody ProductoModelo producto) {	
		try {
			ProductoModelo productos = conversor.entidadAModelo(service.agregar(conversor.modeloAEntidad(producto)));
			return new ResponseEntity<ProductoModelo>(productos, HttpStatus.OK);	
		}catch (Exception e) {			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/{id}")
	public ResponseEntity<ProductoModelo> editar(@PathVariable int id){
		
		try {
			ProductoModelo productos = conversor.entidadAModelo(service.obtenerPorId(id));
			return new ResponseEntity<ProductoModelo>(productos, HttpStatus.OK);	
		}catch (Exception e) {			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@PutMapping
	public ResponseEntity<ProductoModelo> editar(@RequestBody ProductoModelo modelo){
				
		try {
			if (modelo.getId() <= 0) {            
	            throw new RuntimeException("Id no puede ser nulo");
	        }
			ProductoModelo productos = conversor.entidadAModelo(service.editar(conversor.modeloAEntidad(modelo)));
			return new ResponseEntity<ProductoModelo>(productos, HttpStatus.OK);	
		}catch (Exception e) {			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
}
