package com.example.kardex.conversor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.kardex.entidad.PedidoDet;
import com.example.kardex.modelo.PedidoDetModelo;

@Component
public class PedidoDetConversor {

	public PedidoDet modeloAEntidad(PedidoDetModelo modelo)
    {
        PedidoDet entidad = new PedidoDet();

        entidad.setIdPedido(modelo.getIdPedido());
        entidad.setSecuencia(modelo.getSecuencia());
        entidad.setIdProducto(modelo.getIdProducto());
        entidad.setValorUnitario(modelo.getValorUnitario());
        entidad.setValorTotal(modelo.getValorTotal());
        entidad.setCantidad(modelo.getCantidad());

        return entidad;
    }

    public PedidoDetModelo entidadAModelo(PedidoDet entidad) {
        PedidoDetModelo modelo = new PedidoDetModelo();

        modelo.setIdPedido(entidad.getIdPedido());
        modelo.setSecuencia(entidad.getSecuencia());
        modelo.setValorUnitario(entidad.getValorUnitario());
        modelo.setValorTotal(entidad.getValorTotal());
        modelo.setCantidad(entidad.getCantidad());
        modelo.setIdProducto(entidad.getIdProducto());

        return modelo;
    }

    public List<PedidoDet> listaModeloAEntidad(List<PedidoDetModelo> listaModelo) {
        List<PedidoDet> listaEntidad = new ArrayList<>();

        listaModelo.forEach((modelo) -> {
            listaEntidad.add(modeloAEntidad(modelo));
        });

        return listaEntidad;
    }

    public List<PedidoDetModelo> listaEntidadAModelo(List<PedidoDet> listaEntidad) {
        List<PedidoDetModelo> listaModelo = new ArrayList<>();

        listaEntidad.forEach((entity) -> {
            listaModelo.add(entidadAModelo(entity));
        });

        return listaModelo;
    }	
	
}
