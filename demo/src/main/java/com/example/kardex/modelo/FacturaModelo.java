package com.example.kardex.modelo;

import java.util.List;

public class FacturaModelo {
	private int id;
	private int valor;
	private int idMedioDePago;
	private int idUsuario;
	private String fecha;
	private String mensaje;
	private List<FacturaDetModelo> listaProductos;
	
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public List<FacturaDetModelo> getListaProductos() {
		return listaProductos;
	}
	public void setListaProductos(List<FacturaDetModelo> listaProductos) {
		this.listaProductos = listaProductos;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}	
	public int getIdMedioDePago() {
		return idMedioDePago;
	}
	public void setIdMedioDePago(int idMedioDePago) {
		this.idMedioDePago = idMedioDePago;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	
}
