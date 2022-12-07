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

import com.example.kardex.conversor.FacturaConversor;
import com.example.kardex.conversor.FacturaDetConversor;
import com.example.kardex.modelo.FacturaModelo;
import com.example.kardex.modelo.FacturaDetModelo;
import com.example.kardex.service.FacturaService;

@CrossOrigin
@RequestMapping("Facturas")
@RestController
public class FacturaController {

	@Autowired
	private FacturaService service;
	@Autowired
	private FacturaConversor conversor;
	@Autowired
	private FacturaDetConversor detConversor;
	
	@GetMapping
	public ResponseEntity<List<FacturaModelo>> listar() {	
		try {
			List<FacturaModelo> Facturas = conversor.listaEntidadAModelo(service.listar());
			return new ResponseEntity<List<FacturaModelo>>(Facturas, HttpStatus.OK);	
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@PostMapping
	public ResponseEntity<FacturaModelo> agregar(@RequestBody FacturaModelo factura) {	
		try {
			service.setFaturaDet(detConversor.listaModeloAEntidad(factura.getListaProductos()));
			FacturaModelo Facturas = conversor.entidadAModelo(service.agregar(conversor.modeloAEntidad(factura)));
			return new ResponseEntity<FacturaModelo>(Facturas, HttpStatus.OK);	
		}catch (Exception e) {	
			FacturaModelo retorna = new FacturaModelo();
			retorna.setMensaje(e.getMessage());
			return new ResponseEntity<>(retorna, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/{id}")
	public ResponseEntity<FacturaModelo> editar(@PathVariable int id){
		
		try {
			FacturaModelo Facturas = conversor.entidadAModelo(service.obtenerPorId(id));
			return new ResponseEntity<FacturaModelo>(Facturas, HttpStatus.OK);	
		}catch (Exception e) {			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@PutMapping
	public ResponseEntity<FacturaModelo> editar(@RequestBody FacturaModelo modelo){
				
		try {
			if (modelo.getId() <= 0) {            
	            throw new RuntimeException("Id no puede ser nulo");
	        }
			FacturaModelo Facturas = conversor.entidadAModelo(service.editar(conversor.modeloAEntidad(modelo)));
			return new ResponseEntity<FacturaModelo>(Facturas, HttpStatus.OK);	
		}catch (Exception e) {			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
}
