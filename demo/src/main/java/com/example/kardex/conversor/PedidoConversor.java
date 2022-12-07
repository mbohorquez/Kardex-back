package com.example.kardex.conversor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.kardex.entidad.Pedido;
import com.example.kardex.modelo.PedidoModelo;

@Component
public class PedidoConversor {

	public Pedido modeloAEntidad(PedidoModelo modelo)
    {
        Pedido entidad = new Pedido();

        entidad.setId(modelo.getId());
        entidad.setCodigoFactura(modelo.getCodigoFactura());
        entidad.setValor(modelo.getValor());
        entidad.setFecha(modelo.getFecha());
        

        return entidad;
    }

    public PedidoModelo entidadAModelo(Pedido entidad) {
        PedidoModelo modelo = new PedidoModelo();

        modelo.setId(entidad.getId());
        modelo.setCodigoFactura(entidad.getCodigoFactura());
        modelo.setValor(entidad.getValor());
        modelo.setFecha(entidad.getFecha());
        

        return modelo;
    }

    public List<Pedido> listaModeloAEntidad(List<PedidoModelo> listaModelo) {
        List<Pedido> listaEntidad = new ArrayList<>();

        listaModelo.forEach((modelo) -> {
            listaEntidad.add(modeloAEntidad(modelo));
        });

        return listaEntidad;
    }

    public List<PedidoModelo> listaEntidadAModelo(List<Pedido> listaEntidad) {
        List<PedidoModelo> listaModelo = new ArrayList<>();

        listaEntidad.forEach((entity) -> {
            listaModelo.add(entidadAModelo(entity));
        });

        return listaModelo;
    }	
	
}
