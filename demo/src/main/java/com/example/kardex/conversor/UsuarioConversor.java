package com.example.kardex.conversor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.kardex.entidad.Usuario;
import com.example.kardex.modelo.UsuarioModelo;

@Component
public class UsuarioConversor {

	public Usuario modeloAEntidad(UsuarioModelo modelo)
    {
        Usuario entidad = new Usuario();

        entidad.setId(modelo.getId());
        entidad.setNombre(modelo.getNombre());
        entidad.setPassword(modelo.getPassword());
        entidad.setTarjetaCredito(modelo.getTarjetaCredito());
        entidad.setUsuario(modelo.getUsuario());

        return entidad;
    }

    public UsuarioModelo entidadAModelo(Usuario entidad) {
        UsuarioModelo modelo = new UsuarioModelo();

        modelo.setId(entidad.getId());
        modelo.setNombre(entidad.getNombre());
        modelo.setPassword(entidad.getPassword());
        modelo.setTarjetaCredito(entidad.getTarjetaCredito());
        modelo.setUsuario(entidad.getUsuario());

        return modelo;
    }

    public List<Usuario> listaModeloAEntidad(List<UsuarioModelo> listaModelo) {
        List<Usuario> listaEntidad = new ArrayList<>();

        listaModelo.forEach((modelo) -> {
            listaEntidad.add(modeloAEntidad(modelo));
        });

        return listaEntidad;
    }

    public List<UsuarioModelo> listaEntidadAModelo(List<Usuario> listaEntidad) {
        List<UsuarioModelo> listaModelo = new ArrayList<>();

        listaEntidad.forEach((entity) -> {
            listaModelo.add(entidadAModelo(entity));
        });

        return listaModelo;
    }	
	
}
