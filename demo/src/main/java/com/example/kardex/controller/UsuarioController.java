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

import com.example.kardex.conversor.UsuarioConversor;
import com.example.kardex.modelo.UsuarioModelo;
import com.example.kardex.service.UsuarioService;

@CrossOrigin
@RequestMapping("Usuarios")
@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	@Autowired
	private UsuarioConversor conversor;
	
	@GetMapping
	public ResponseEntity<List<UsuarioModelo>> listar() {	
		try {
			List<UsuarioModelo> Usuarios = conversor.listaEntidadAModelo(service.listar());
			return new ResponseEntity<List<UsuarioModelo>>(Usuarios, HttpStatus.OK);	
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@PostMapping
	public ResponseEntity<UsuarioModelo> agregar(@RequestBody UsuarioModelo Usuario) {	
		try {
			UsuarioModelo Usuarios = conversor.entidadAModelo(service.agregar(conversor.modeloAEntidad(Usuario)));
			return new ResponseEntity<UsuarioModelo>(Usuarios, HttpStatus.OK);	
		}catch (Exception e) {			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioModelo> editar(@PathVariable int id){
		
		try {
			UsuarioModelo Usuarios = conversor.entidadAModelo(service.obtenerPorId(id));
			return new ResponseEntity<UsuarioModelo>(Usuarios, HttpStatus.OK);	
		}catch (Exception e) {			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@PutMapping
	public ResponseEntity<UsuarioModelo> editar(@RequestBody UsuarioModelo modelo){
				
		try {
			if (modelo.getId() <= 0) {            
	            throw new RuntimeException("Id no puede ser nulo");
	        }
			UsuarioModelo Usuarios = conversor.entidadAModelo(service.editar(conversor.modeloAEntidad(modelo)));
			return new ResponseEntity<UsuarioModelo>(Usuarios, HttpStatus.OK);	
		}catch (Exception e) {			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
}
