package com.example.kardex.entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "producto")
public class Producto {

	@Id    @GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="id")
	private Integer id;
	@Column(name="nombre")
	private String nombre;
	@Column(name="minimo")
	private Integer minimo;
	@Column(name="maximo")
	private Integer maximo;
	@Column(name="estado")
	private Integer estado;
	@Column(name="cantidad")
	private Integer cantidad;
	@Column(name="valor")
	private Integer valor;
	
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}	
	public Integer getValor() {
		return valor;
	}
	public void setValor(Integer valor) {
		this.valor = valor;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getMinimo() {
		return minimo;
	}
	public void setMinimo(Integer minimo) {
		this.minimo = minimo;
	}
	public Integer getMaximo() {
		return maximo;
	}
	public void setMaximo(Integer maximo) {
		this.maximo = maximo;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	
}
