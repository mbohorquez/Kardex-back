package com.example.kardex.conversor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.kardex.entidad.Factura;
import com.example.kardex.modelo.FacturaModelo;

@Component
public class FacturaConversor {

	public Factura modeloAEntidad(FacturaModelo modelo)
    {
        Factura entidad = new Factura();

        entidad.setId(modelo.getId());
        entidad.setValor(modelo.getValor());
        entidad.setIdMedioDePago(modelo.getIdMedioDePago());
        entidad.setIdUsuario(modelo.getIdUsuario());
        entidad.setFecha(modelo.getFecha());

        return entidad;
    }

    public FacturaModelo entidadAModelo(Factura entidad) {
        FacturaModelo modelo = new FacturaModelo();

        modelo.setId(entidad.getId());
        modelo.setValor(entidad.getValor());
        modelo.setIdMedioDePago(entidad.getIdMedioDePago());
        modelo.setIdUsuario(entidad.getIdUsuario());
        modelo.setFecha(entidad.getFecha());

        return modelo;
    }

    public List<Factura> listaModeloAEntidad(List<FacturaModelo> listaModelo) {
        List<Factura> listaEntidad = new ArrayList<>();

        listaModelo.forEach((modelo) -> {
            listaEntidad.add(modeloAEntidad(modelo));
        });

        return listaEntidad;
    }

    public List<FacturaModelo> listaEntidadAModelo(List<Factura> listaEntidad) {
        List<FacturaModelo> listaModelo = new ArrayList<>();

        listaEntidad.forEach((entity) -> {
            listaModelo.add(entidadAModelo(entity));
        });

        return listaModelo;
    }	
	
}
