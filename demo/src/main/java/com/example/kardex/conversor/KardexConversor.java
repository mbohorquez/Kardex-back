package com.example.kardex.conversor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.kardex.entidad.Kardex;
import com.example.kardex.modelo.KardexModelo;

@Component
public class KardexConversor {

	public Kardex modeloAEntidad(KardexModelo modelo)
    {
        Kardex entidad = new Kardex();

        entidad.setSecuencia(modelo.getSecuencia());
        entidad.setFecha(modelo.getFecha());
        entidad.setValorUnitario(modelo.getValorUnitario());
        entidad.setEntradaValor(modelo.getEntradaValor());
        entidad.setEntradaCantidad(modelo.getEntradaCantidad());
        entidad.setSalidaValor(modelo.getSalidaValor());
        entidad.setSalidaCantidad(modelo.getSalidaCantidad());
        entidad.setSaldoValor(modelo.getSaldoValor());
        entidad.setSaldoCantidad(modelo.getSaldoCantidad());
        entidad.setIdPedido(modelo.getIdPedido());
        entidad.setIdFactura(modelo.getIdFactura());
        entidad.setIdProducto(modelo.getIdProducto());
        
        return entidad;
    }

    public KardexModelo entidadAModelo(Kardex entidad) {
        KardexModelo modelo = new KardexModelo();

        modelo.setSecuencia(entidad.getSecuencia());
        modelo.setFecha(entidad.getFecha());
        modelo.setValorUnitario(entidad.getValorUnitario());
        modelo.setEntradaValor(entidad.getEntradaValor() == null ? 0 : entidad.getEntradaValor());
        modelo.setEntradaCantidad(entidad.getEntradaCantidad() == null ? 0 : entidad.getEntradaCantidad());
        modelo.setSalidaValor(entidad.getSalidaValor() == null ? 0 : entidad.getSalidaValor());
        modelo.setSalidaCantidad(entidad.getSalidaCantidad() == null ? 0 : entidad.getSalidaCantidad());
        modelo.setSaldoValor(entidad.getSaldoValor());
        modelo.setSaldoCantidad(entidad.getSaldoCantidad());
        modelo.setIdPedido(entidad.getIdPedido() == null ? 0 : entidad.getIdPedido());
        modelo.setIdFactura(entidad.getIdFactura() == null ? 0 : entidad.getIdFactura());
        modelo.setIdProducto(entidad.getIdProducto());

        return modelo;
    }

    public List<Kardex> listaModeloAEntidad(List<KardexModelo> listaModelo) {
        List<Kardex> listaEntidad = new ArrayList<>();

        listaModelo.forEach((modelo) -> {
            listaEntidad.add(modeloAEntidad(modelo));
        });

        return listaEntidad;
    }

    public List<KardexModelo> listaEntidadAModelo(List<Kardex> listaEntidad) {
        List<KardexModelo> listaModelo = new ArrayList<>();

        listaEntidad.forEach((entity) -> {
            listaModelo.add(entidadAModelo(entity));
        });

        return listaModelo;
    }	
	
}
