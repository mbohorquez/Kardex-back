package com.example.kardex.conversor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.kardex.entidad.Producto;
import com.example.kardex.modelo.ProductoModelo;

@Component
public class ProductoConversor {

	
	public Producto modeloAEntidad(ProductoModelo modelo)
    {
        Producto entidad = new Producto();

        entidad.setId(modelo.getId());
        entidad.setNombre(modelo.getNombre());
        entidad.setMinimo(modelo.getMinimo());
        entidad.setMaximo(modelo.getMaximo());
        entidad.setEstado(modelo.getEstado());
        entidad.setCantidad(modelo.getCantidad());
        entidad.setValor(modelo.getValor());

        return entidad;
    }

    public ProductoModelo entidadAModelo(Producto entidad) {
        ProductoModelo modelo = new ProductoModelo();

        modelo.setId(entidad.getId());
        modelo.setNombre(entidad.getNombre());
        modelo.setMinimo(entidad.getMinimo());
        modelo.setMaximo(entidad.getMaximo());
        modelo.setEstado(entidad.getEstado());
        modelo.setCantidad(entidad.getCantidad());
        modelo.setValor(entidad.getValor());

        return modelo;
    }

    public List<Producto> listaModeloAEntidad(List<ProductoModelo> listaModelo) {
        List<Producto> listaEntidad = new ArrayList<>();

        listaModelo.forEach((modelo) -> {
            listaEntidad.add(modeloAEntidad(modelo));
        });

        return listaEntidad;
    }

    public List<ProductoModelo> listaEntidadAModelo(List<Producto> listaEntidad) {
        List<ProductoModelo> listaModelo = new ArrayList<>();

        listaEntidad.forEach((entity) -> {
            listaModelo.add(entidadAModelo(entity));
        });

        return listaModelo;
    }	
}
