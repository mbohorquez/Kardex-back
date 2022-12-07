package com.example.kardex.conversor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.kardex.entidad.FacturaDet;
import com.example.kardex.modelo.FacturaDetModelo;

@Component
public class FacturaDetConversor {

	public FacturaDet modeloAEntidad(FacturaDetModelo modelo)
    {
        FacturaDet entidad = new FacturaDet();

        entidad.setIdFactura(modelo.getIdFactura());
        entidad.setSecuencia(modelo.getSecuencia());
        entidad.setIdProducto(modelo.getIdProducto());
        entidad.setValorUnitario(modelo.getValorUnitario());
        entidad.setValorTotal(modelo.getValorTotal());
        entidad.setCantidad(modelo.getCantidad());

        return entidad;
    }

    public FacturaDetModelo entidadAModelo(FacturaDet entidad) {
        FacturaDetModelo modelo = new FacturaDetModelo();

        modelo.setIdFactura(entidad.getIdFactura());
        modelo.setSecuencia(entidad.getSecuencia());
        modelo.setIdProducto(entidad.getIdProducto());
        modelo.setValorUnitario(entidad.getValorUnitario());
        modelo.setValorTotal(entidad.getValorTotal());
        modelo.setCantidad(entidad.getCantidad());

        return modelo;
    }

    public List<FacturaDet> listaModeloAEntidad(List<FacturaDetModelo> listaModelo) {
        List<FacturaDet> listaEntidad = new ArrayList<>();

        listaModelo.forEach((modelo) -> {
            listaEntidad.add(modeloAEntidad(modelo));
        });

        return listaEntidad;
    }

    public List<FacturaDetModelo> listaEntidadAModelo(List<FacturaDet> listaEntidad) {
        List<FacturaDetModelo> listaModelo = new ArrayList<>();

        listaEntidad.forEach((entity) -> {
            listaModelo.add(entidadAModelo(entity));
        });

        return listaModelo;
    }	
	
}
