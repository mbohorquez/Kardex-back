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

import com.example.kardex.conversor.PedidoConversor;
import com.example.kardex.conversor.PedidoDetConversor;
import com.example.kardex.modelo.FacturaModelo;
import com.example.kardex.modelo.PedidoModelo;
import com.example.kardex.service.PedidoService;

@CrossOrigin
@RequestMapping("Pedidos")
@RestController
public class PedidoController {
	
	@Autowired
	private PedidoService service;
	@Autowired
	private PedidoConversor conversor;
	@Autowired
	private PedidoDetConversor detConversor;
	
	@GetMapping
	public ResponseEntity<List<PedidoModelo>> listar() {	
		try {
			List<PedidoModelo> Pedidos = conversor.listaEntidadAModelo(service.listar());
			return new ResponseEntity<List<PedidoModelo>>(Pedidos, HttpStatus.OK);	
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@PostMapping
	public ResponseEntity<PedidoModelo> agregar(@RequestBody PedidoModelo pedido) {	
		try {
			service.setPedidoDet(detConversor.listaModeloAEntidad(pedido.getListaProductos()));
			pedido = conversor.entidadAModelo(service.agregar(conversor.modeloAEntidad(pedido)));
			return new ResponseEntity<PedidoModelo>(pedido, HttpStatus.OK);	
		}catch (Exception e) {	
			PedidoModelo retorna = new PedidoModelo();
			retorna.setMensaje(e.getMessage());
			return new ResponseEntity<>(retorna, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/{id}")
	public ResponseEntity<PedidoModelo> editar(@PathVariable int id){
		
		try {
			PedidoModelo Pedidos = conversor.entidadAModelo(service.obtenerPorId(id));
			return new ResponseEntity<PedidoModelo>(Pedidos, HttpStatus.OK);	
		}catch (Exception e) {			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@PutMapping
	public ResponseEntity<PedidoModelo> editar(@RequestBody PedidoModelo modelo){
				
		try {
			if (modelo.getId() <= 0) {            
	            throw new RuntimeException("Id no puede ser nulo");
	        }
			PedidoModelo Pedidos = conversor.entidadAModelo(service.editar(conversor.modeloAEntidad(modelo)));
			return new ResponseEntity<PedidoModelo>(Pedidos, HttpStatus.OK);	
		}catch (Exception e) {			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
