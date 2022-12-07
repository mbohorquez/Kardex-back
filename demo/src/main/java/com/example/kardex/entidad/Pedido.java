package com.example.kardex.entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedido")
public class Pedido {

	@Id    @GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="id")
	private Integer id;
	@Column(name="codigo_factura")
	private String codigoFactura;
	@Column(name="valor")
	private Integer valor;
	@Column(name="fecha")
	private String fecha;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCodigoFactura() {
		return codigoFactura;
	}
	public void setCodigoFactura(String codigoFactura) {
		this.codigoFactura = codigoFactura;
	}
	public Integer getValor() {
		return valor;
	}
	public void setValor(Integer valor) {
		this.valor = valor;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
}
