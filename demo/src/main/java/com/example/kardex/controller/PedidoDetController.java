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

import com.example.kardex.conversor.PedidoDetConversor;
import com.example.kardex.modelo.PedidoDetModelo;
import com.example.kardex.service.PedidoDetService;

@CrossOrigin
@RequestMapping("PedidoDet")
@RestController
public class PedidoDetController {

	@Autowired
	private PedidoDetService service;
	@Autowired
	private PedidoDetConversor conversor;
	
	@GetMapping
	public ResponseEntity<List<PedidoDetModelo>> listar() {	
		try {
			List<PedidoDetModelo> PedidoDets = conversor.listaEntidadAModelo(service.listar());
			return new ResponseEntity<List<PedidoDetModelo>>(PedidoDets, HttpStatus.OK);	
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@PostMapping
	public ResponseEntity<PedidoDetModelo> agregar(@RequestBody PedidoDetModelo PedidoDet) {	
		try {
			PedidoDetModelo PedidoDets = conversor.entidadAModelo(service.agregar(conversor.modeloAEntidad(PedidoDet)));
			return new ResponseEntity<PedidoDetModelo>(PedidoDets, HttpStatus.OK);	
		}catch (Exception e) {			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/{id}")
	public ResponseEntity<PedidoDetModelo> editar(@PathVariable int id){
		
		try {
			PedidoDetModelo PedidoDets = conversor.entidadAModelo(service.obtenerPorId(id));
			return new ResponseEntity<PedidoDetModelo>(PedidoDets, HttpStatus.OK);	
		}catch (Exception e) {			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@GetMapping("IdPedido/{id}")
	public ResponseEntity<List<PedidoDetModelo>> listarIdPedido(@PathVariable int id) {	
		try {
			List<PedidoDetModelo> PedidoDets = conversor.listaEntidadAModelo(service.listarIdPedido(id));
			return new ResponseEntity<List<PedidoDetModelo>>(PedidoDets, HttpStatus.OK);	
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PutMapping
	public ResponseEntity<PedidoDetModelo> editar(@RequestBody PedidoDetModelo modelo){
				
		try {
			if (modelo.getSecuencia() <= 0) {            
	            throw new RuntimeException("Id no puede ser nulo");
	        }
			PedidoDetModelo PedidoDets = conversor.entidadAModelo(service.editar(conversor.modeloAEntidad(modelo)));
			return new ResponseEntity<PedidoDetModelo>(PedidoDets, HttpStatus.OK);	
		}catch (Exception e) {			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
}
