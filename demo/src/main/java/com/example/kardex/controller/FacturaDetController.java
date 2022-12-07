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

import com.example.kardex.conversor.FacturaDetConversor;
import com.example.kardex.modelo.FacturaDetModelo;
import com.example.kardex.service.FacturaDetService;

@CrossOrigin
@RequestMapping("FacturaDet")
@RestController
public class FacturaDetController {

	@Autowired
	private FacturaDetService service;
	@Autowired
	private FacturaDetConversor conversor;
	
	@GetMapping
	public ResponseEntity<List<FacturaDetModelo>> listar() {	
		try {
			List<FacturaDetModelo> FacturaDets = conversor.listaEntidadAModelo(service.listar());
			return new ResponseEntity<List<FacturaDetModelo>>(FacturaDets, HttpStatus.OK);	
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@PostMapping
	public ResponseEntity<FacturaDetModelo> agregar(@RequestBody FacturaDetModelo FacturaDet) {	
		try {
			FacturaDetModelo FacturaDets = conversor.entidadAModelo(service.agregar(conversor.modeloAEntidad(FacturaDet)));
			return new ResponseEntity<FacturaDetModelo>(FacturaDets, HttpStatus.OK);	
		}catch (Exception e) {			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/{id}")
	public ResponseEntity<FacturaDetModelo> editar(@PathVariable int id){
		
		try {
			FacturaDetModelo FacturaDets = conversor.entidadAModelo(service.obtenerPorId(id));
			return new ResponseEntity<FacturaDetModelo>(FacturaDets, HttpStatus.OK);	
		}catch (Exception e) {			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@GetMapping("IdFactura/{id}")
	public ResponseEntity<List<FacturaDetModelo>> listarIdFactura(@PathVariable int id) {	
		try {
			List<FacturaDetModelo> FacturaDets = conversor.listaEntidadAModelo(service.listarIdFactura(id));
			return new ResponseEntity<List<FacturaDetModelo>>(FacturaDets, HttpStatus.OK);	
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PutMapping
	public ResponseEntity<FacturaDetModelo> editar(@RequestBody FacturaDetModelo modelo){
				
		try {
			if (modelo.getSecuencia() <= 0) {            
	            throw new RuntimeException("Id no puede ser nulo");
	        }
			FacturaDetModelo FacturaDets = conversor.entidadAModelo(service.editar(conversor.modeloAEntidad(modelo)));
			return new ResponseEntity<FacturaDetModelo>(FacturaDets, HttpStatus.OK);	
		}catch (Exception e) {			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
}
