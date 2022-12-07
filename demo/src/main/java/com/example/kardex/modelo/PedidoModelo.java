package com.example.kardex.modelo;

import java.util.List;

public class PedidoModelo {
	private int id;
	private String codigoFactura;
	private int idProducto;
	private int cantidad;
	private int valor;
	private String fecha;
	private List<PedidoDetModelo> listaProductos;
	private String Mensaje;
	
	public String getMensaje() {
		return Mensaje;
	}
	public void setMensaje(String mensaje) {
		Mensaje = mensaje;
	}
	public List<PedidoDetModelo> getListaProductos() {
		return listaProductos;
	}
	public void setListaProductos(List<PedidoDetModelo> listaProductos) {
		this.listaProductos = listaProductos;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCodigoFactura() {
		return codigoFactura;
	}
	public void setCodigoFactura(String codigoFactura) {
		this.codigoFactura = codigoFactura;
	}
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
}
