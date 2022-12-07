package com.example.kardex.entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "kardex")
public class Kardex {

	@Id    @GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="secuencia")
	private Integer secuencia;	
	@Column(name="fecha")
	private String fecha;
	@Column(name="valor_unitario")
	private Integer valorUnitario;
	@Column(name="entrada_cantidad")
	private Integer entradaCantidad;
	@Column(name="entrada_valor")
	private Integer entradaValor;
	@Column(name="salida_cantidad")
	private Integer salidaCantidad;
	@Column(name="salida_valor")
	private Integer salidaValor;
	@Column(name="saldo_cantidad")
	private Integer saldoCantidad;
	@Column(name="saldo_valor")
	private Integer saldoValor;
	@Column(name="id_factura")
	private Integer idFactura;
	@Column(name="id_pedido")
	private Integer idPedido;
	@Column(name="id_producto")
	private Integer idProducto;
		
	public Integer getSaldoValor() {
		return saldoValor;
	}
	public void setSaldoValor(Integer saldoValor) {
		this.saldoValor = saldoValor;
	}
	public Integer getSalidaCantidad() {
		return salidaCantidad;
	}
	public void setSalidaCantidad(Integer salidaCantidad) {
		this.salidaCantidad = salidaCantidad;
	}
	public Integer getSecuencia() {
		return secuencia;
	}
	public void setSecuencia(Integer secuencia) {
		this.secuencia = secuencia;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Integer getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(Integer valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	public Integer getEntradaCantidad() {
		return entradaCantidad;
	}
	public void setEntradaCantidad(Integer entradaCantidad) {
		this.entradaCantidad = entradaCantidad;
	}
	public Integer getEntradaValor() {
		return entradaValor;
	}
	public void setEntradaValor(Integer entradaValor) {
		this.entradaValor = entradaValor;
	}
	public Integer getSalidaValor() {
		return salidaValor;
	}
	public void setSalidaValor(Integer salidaValor) {
		this.salidaValor = salidaValor;
	}
	public Integer getSaldoCantidad() {
		return saldoCantidad;
	}
	public void setSaldoCantidad(Integer saldoCantidad) {
		this.saldoCantidad = saldoCantidad;
	}
	public Integer getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(Integer idFactura) {
		this.idFactura = idFactura;
	}
	public Integer getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}
	public Integer getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}	
	
}
