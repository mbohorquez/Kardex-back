package com.example.kardex.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.kardex.entidad.Usuario;
import com.example.kardex.repositorio.UsuarioJPA;

@Service
public class UsuarioService{
	
	@Autowired
	private UsuarioJPA jpa;
	
	private PasswordEncoder passwordEncoder;
	
	public UsuarioService(UsuarioJPA usuario) {
		this.jpa = usuario;
		this.passwordEncoder = new BCryptPasswordEncoder();
		
	}
	
	@Transactional(readOnly = true)
	public List<Usuario> listar() {
		List<Usuario> list = new ArrayList<>();
        jpa.findAll().forEach(list::add);
		return list;
	}

    @Transactional(readOnly = true)
	public Usuario obtenerPorId(int id) {
		
		Optional<Usuario> item = jpa.findById(id);
        if (!item.isPresent()) {            
            return null;
        }
		return item.get();
	}
    @Transactional(readOnly = false)
	public Usuario agregar(Usuario entidad) {
		Optional<Usuario> item = jpa.findById(entidad.getId());

        if (item.isPresent()) {            
            throw new RuntimeException("Error Llave Primaria");
        }
        
        String password = this.passwordEncoder.encode(entidad.getPassword());        
		String tarjetaCredito = this.passwordEncoder.encode(entidad.getTarjetaCredito());
		entidad.setPassword(password);
		entidad.setTarjetaCredito(tarjetaCredito);
		return jpa.save(entidad);
	}
    @Transactional(readOnly = false)
	public Usuario editar(Usuario entidad) {
		Optional<Usuario> item = jpa.findById(entidad.getId());

        if (!item.isPresent()) {            
            throw new RuntimeException("Usuario no existe");
        }
        String password = this.passwordEncoder.encode(entidad.getPassword());
		String tarjetaCredito = this.passwordEncoder.encode(entidad.getTarjetaCredito());
		entidad.setPassword(password);
		entidad.setTarjetaCredito(tarjetaCredito);
		return jpa.save(entidad);
	}

}
