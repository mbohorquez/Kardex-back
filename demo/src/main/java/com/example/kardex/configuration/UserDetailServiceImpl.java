package com.example.kardex.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.kardex.entidad.Usuario;
import com.example.kardex.repositorio.UsuarioJPA;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
	
	@Autowired
	private UsuarioJPA dao;
	
	@Override
	public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
		Usuario user = dao.findByUsuario(usuario);
		if (user == null) {
			return (UserDetails) new UsernameNotFoundException("El usuario "+ usuario +" no existe");
		}
		return new UserDetailsImp(user);
	}
}
