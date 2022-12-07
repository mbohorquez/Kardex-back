package com.example.kardex.entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedido_det")
public class PedidoDet {

	@Id    @GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="secuencia")
	private Integer secuencia;
	@Column(name="id_pedido")
	private Integer idPedido;	
	@Column(name="valorUnitario")
	private Integer valorUnitario;
	@Column(name="id_producto")
	private Integer idProducto;
	@Column(name="valorTotal")
	private Integer valorTotal;
	@Column(name="cantidad")
	private Integer cantidad;
	
	public Integer getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}
	public Integer getSecuencia() {
		return secuencia;
	}
	public void setSecuencia(Integer secuencia) {
		this.secuencia = secuencia;
	}
	public Integer getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}	
	public Integer getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(Integer valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	public Integer getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Integer valorTotal) {
		this.valorTotal = valorTotal;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
}
