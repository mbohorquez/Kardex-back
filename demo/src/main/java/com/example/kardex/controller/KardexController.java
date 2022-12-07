package com.example.kardex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kardex.conversor.KardexConversor;
import com.example.kardex.modelo.KardexModelo;
import com.example.kardex.service.KardexService;

@CrossOrigin
@RequestMapping("Kardex")
@RestController
public class KardexController {

	@Autowired
	private KardexService service;
	@Autowired
	private KardexConversor conversor;
	
	@GetMapping
	public ResponseEntity<List<KardexModelo>> listar() {	
		try {
			List<KardexModelo> Kardexs = conversor.listaEntidadAModelo(service.listar());
			return new ResponseEntity<List<KardexModelo>>(Kardexs, HttpStatus.OK);	
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@GetMapping("/{id}")
	public ResponseEntity<KardexModelo> editar(@PathVariable int id){
		
		try {
			KardexModelo Kardexs = conversor.entidadAModelo(service.obtenerPorId(id));
			return new ResponseEntity<KardexModelo>(Kardexs, HttpStatus.OK);	
		}catch (Exception e) {			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@GetMapping("IdProducto/{id}")
	public ResponseEntity<List<KardexModelo>> listarIdFactura(@PathVariable int id) {	
		try {
			List<KardexModelo> Kardexs = conversor.listaEntidadAModelo(service.listarIdProducto(id));
			return new ResponseEntity<List<KardexModelo>>(Kardexs, HttpStatus.OK);	
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
}
