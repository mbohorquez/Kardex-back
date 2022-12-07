package com.example.kardex.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.kardex.entidad.Factura;
import com.example.kardex.entidad.FacturaDet;
import com.example.kardex.entidad.Kardex;
import com.example.kardex.entidad.PedidoDet;
import com.example.kardex.entidad.Producto;
import com.example.kardex.repositorio.FacturaDetJPA;
import com.example.kardex.repositorio.FacturaJPA;
import com.example.kardex.repositorio.KardexJPA;
import com.example.kardex.repositorio.ProductoJPA;

@Service
public class FacturaService {

	@Autowired
	private FacturaJPA jpa;
	
	@Autowired
	private FacturaDetJPA detJpa;
	
	@Autowired
	private ProductoJPA productoJpa;
	
	@Autowired
	private KardexJPA kardexJpa;
	
	private List<FacturaDet> listFacturaDet;
	
	public void setFaturaDet(List<FacturaDet> modeloDet) {
		this.listFacturaDet = modeloDet;
	}
    
    @Transactional(readOnly = true)
	public List<Factura> listar() {
		List<Factura> list = new ArrayList<>();
        jpa.findAll().forEach(list::add);
		return list;
	}

    @Transactional(readOnly = true)
	public Factura obtenerPorId(int id) {
		
		Optional<Factura> item = jpa.findById(id);
        if (!item.isPresent()) {            
            return null;
        }
		return item.get();
	}
    @Transactional(readOnly = false)
	public Factura agregar(Factura entidad) {
		Optional<Factura> item = jpa.findById(entidad.getId());

        if (item.isPresent()) {            
            throw new RuntimeException("Error Llave Primaria");
        }
        
        int valor = 0;
        List<Producto> listProducto = new ArrayList<>();
        for(FacturaDet facturaDet: listFacturaDet) {
        	Optional<Producto> producto = productoJpa.findById(facturaDet.getIdProducto());
        	if(!producto.isPresent()) {
        		throw new RuntimeException("no se encontro el producto id:"+facturaDet.getIdProducto());
        	}
        	if(producto.get().getCantidad() < facturaDet.getCantidad()) {
        		throw new RuntimeException("el producto id:"+facturaDet.getIdProducto()+" excede existencia actual:"+producto.get().getCantidad());
        	}
        	
        	Producto productoCongelado = new Producto();
        	productoCongelado.setCantidad(producto.get().getCantidad());
        	productoCongelado.setId(producto.get().getId());
        	productoCongelado.setValor(producto.get().getValor());
        	listProducto.add(productoCongelado);
        	
        	facturaDet.setValorUnitario(producto.get().getValor());
        	facturaDet.setValorTotal(facturaDet.getCantidad() * producto.get().getValor());
        	valor += facturaDet.getCantidad() * producto.get().getValor();
        	producto.get().setCantidad(producto.get().getCantidad() - facturaDet.getCantidad());
        	productoJpa.save(producto.get());
        }
        
        String fechaActual = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                .format(LocalDateTime.now());        
        entidad.setFecha(fechaActual);	
        entidad.setValor(valor);
		Factura retorna = jpa.save(entidad); 
		
		for(FacturaDet facturaDet: listFacturaDet) {
			facturaDet.setIdFactura(retorna.getId());
			detJpa.save(facturaDet);
			for(Producto producto : listProducto) {
				if(producto.getId() == facturaDet.getIdProducto()) {
					insertaKardex(producto, facturaDet);	
				}
				
			}
		}
		
		return retorna;
	}
    @Transactional(readOnly = false)
	public Factura editar(Factura entidad) {
		Optional<Factura> item = jpa.findById(entidad.getId());

        if (!item.isPresent()) {            
            throw new RuntimeException("Factura no existe");
        }
		return jpa.save(entidad);
	}
    
private void insertaKardex(Producto producto, FacturaDet pedidoDet) {
    	
    	String fechaActual = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                .format(LocalDateTime.now());
    	
    	Kardex kEntidad = new Kardex();
    	
    	kEntidad.setIdProducto(producto.getId());
    	kEntidad.setIdFactura(pedidoDet.getIdFactura());
    	
    	kEntidad.setValorUnitario(((producto.getCantidad())*producto.getValor() + pedidoDet.getValorTotal())/((producto.getCantidad())+pedidoDet.getCantidad()));
    	
    	kEntidad.setSalidaCantidad(pedidoDet.getCantidad());
    	kEntidad.setSalidaValor(pedidoDet.getValorTotal());
    	kEntidad.setSaldoCantidad(producto.getCantidad()-pedidoDet.getCantidad());
    	kEntidad.setSaldoValor(kEntidad.getValorUnitario()*kEntidad.getSaldoCantidad());
    	kEntidad.setFecha(fechaActual);    	
    	
    	kardexJpa.save(kEntidad );    	
    	
    	Optional<Producto> productoAnterior = productoJpa.findById(pedidoDet.getIdProducto());
    	if(!productoAnterior.isPresent()) {
    		throw new RuntimeException("no se encontro el producto id:");
    	}
    	productoAnterior.get().setValor(kEntidad.getValorUnitario());
    	productoJpa.save(productoAnterior.get());
    	
    }
	
}
