package com.example.kardex.modelo;

public class KardexModelo {
	
	private int secuencia;	
	private String fecha;
	private int valorUnitario;
	private int entradaCantidad;
	private int entradaValor;
	private int salidaCantidad;
	private int salidaValor;
	private int saldoCantidad;
	private int saldoValor;
	private int idFactura;
	private int idPedido;
	private int idProducto;
		
	public int getSaldoValor() {
		return saldoValor;
	}
	public void setSaldoValor(int saldoValor) {
		this.saldoValor = saldoValor;
	}
	public int getSalidaCantidad() {
		return salidaCantidad;
	}
	public void setSalidaCantidad(int salidaCantidad) {
		this.salidaCantidad = salidaCantidad;
	}
	public int getSecuencia() {
		return secuencia;
	}
	public void setSecuencia(int secuencia) {
		this.secuencia = secuencia;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public int getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(int valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	public int getEntradaCantidad() {
		return entradaCantidad;
	}
	public void setEntradaCantidad(int entradaCantidad) {
		this.entradaCantidad = entradaCantidad;
	}
	public int getEntradaValor() {
		return entradaValor;
	}
	public void setEntradaValor(int entradaValor) {
		this.entradaValor = entradaValor;
	}
	public int getSalidaValor() {
		return salidaValor;
	}
	public void setSalidaValor(int salidaValor) {
		this.salidaValor = salidaValor;
	}
	public int getSaldoCantidad() {
		return saldoCantidad;
	}
	public void setSaldoCantidad(int saldoCantidad) {
		this.saldoCantidad = saldoCantidad;
	}
	public int getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}
	public int getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	
}
