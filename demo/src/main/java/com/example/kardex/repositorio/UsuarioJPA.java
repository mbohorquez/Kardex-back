package com.example.kardex.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.example.kardex.entidad.Usuario;

public interface UsuarioJPA extends CrudRepository<Usuario, Integer> {
		Usuario findByUsuario(String usuario);
}
