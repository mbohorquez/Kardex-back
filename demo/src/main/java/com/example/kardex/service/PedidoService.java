package com.example.kardex.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.kardex.entidad.Kardex;
import com.example.kardex.entidad.Pedido;
import com.example.kardex.entidad.PedidoDet;
import com.example.kardex.entidad.Producto;
import com.example.kardex.repositorio.KardexJPA;
import com.example.kardex.repositorio.PedidoDetJPA;
import com.example.kardex.repositorio.PedidoJPA;
import com.example.kardex.repositorio.ProductoJPA;

@Service
public class PedidoService{
	
	@Autowired
	private PedidoJPA jpa;
	
	@Autowired
	private PedidoDetJPA detJpa;
	
	@Autowired
	private ProductoJPA productoJpa;
	
	@Autowired
	private KardexJPA kardexJpa;
	
	private List<PedidoDet> listPedidoDet;
	
	public void setPedidoDet(List<PedidoDet> listModelo) {
		this.listPedidoDet = listModelo;
	}
    
    @Transactional(readOnly = true)
	public List<Pedido> listar() {
		List<Pedido> list = new ArrayList<>();
        jpa.findAll().forEach(list::add);
		return list;
	}

    @Transactional(readOnly = true)
	public Pedido obtenerPorId(int id) {
		
		Optional<Pedido> item = jpa.findById(id);
        if (!item.isPresent()) {            
            return null;
        }
		return item.get();
	}
    @Transactional(readOnly = false)
	public Pedido agregar(Pedido entidad) {
		Optional<Pedido> item = jpa.findById(entidad.getId());

        if (item.isPresent()) {            
            throw new RuntimeException("Error Llave Primaria");
        }
        
        int valor = 0;
        List<Producto> listProducto = new ArrayList<>();
        for(PedidoDet PedidoDet: listPedidoDet) {
        	Optional<Producto> producto = productoJpa.findById(PedidoDet.getIdProducto());
        	if(!producto.isPresent()) {
        		throw new RuntimeException("no se encontro el producto id:"+PedidoDet.getIdProducto());
        	}
        	
        	PedidoDet.setValorUnitario(PedidoDet.getValorTotal()/PedidoDet.getCantidad());        	
        	valor += PedidoDet.getValorTotal();
        	
        	Producto productoCongelado = new Producto();
        	productoCongelado.setCantidad(producto.get().getCantidad());
        	productoCongelado.setId(producto.get().getId());
        	productoCongelado.setValor(producto.get().getValor());
        	listProducto.add(productoCongelado);
        	producto.get().setCantidad(producto.get().getCantidad() + PedidoDet.getCantidad());
        	productoJpa.save(producto.get());
        	
        }
        
        String fechaActual = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                .format(LocalDateTime.now());
        entidad.setValor(valor);
        entidad.setFecha(fechaActual);		
		Pedido retorna = jpa.save(entidad); 
		
		for(PedidoDet pedidoDet: listPedidoDet) {
			pedidoDet.setIdPedido(retorna.getId());
			detJpa.save(pedidoDet);
			for(Producto producto : listProducto) {
				if(producto.getId() == pedidoDet.getIdProducto()) {
					insertaKardex(producto, pedidoDet);	
				}
				
			}
			
		}
		
		return retorna;
        
	}
    @Transactional(readOnly = false)
	public Pedido editar(Pedido entidad) {
		Optional<Pedido> item = jpa.findById(entidad.getId());

        if (!item.isPresent()) {            
            throw new RuntimeException("Pedido no existe");
        }
		return jpa.save(entidad);
	}
    
    private void insertaKardex(Producto producto, PedidoDet pedidoDet) {
    	
    	String fechaActual = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                .format(LocalDateTime.now());
    	
    	Kardex kEntidad = new Kardex();
    	
    	kEntidad.setIdProducto(producto.getId());
    	kEntidad.setIdPedido(pedidoDet.getIdPedido());
    	
    	kEntidad.setValorUnitario(((producto.getCantidad())*producto.getValor() + pedidoDet.getValorTotal())/((producto.getCantidad())+pedidoDet.getCantidad()));
    	
    	kEntidad.setEntradaCantidad(pedidoDet.getCantidad());
    	kEntidad.setEntradaValor(pedidoDet.getValorTotal());
    	kEntidad.setSaldoCantidad(producto.getCantidad()+pedidoDet.getCantidad());
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
