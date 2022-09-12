package com.uce.edu.demo.repository.modelo;

import java.math.BigDecimal;

public class ClienteVip {
	
	private String nombre;
	private String apellido;
	private String cedula;
	private String genero;
	private String tipoRegistro;
	private BigDecimal valorIVA;
	private BigDecimal valorTotal;

	public ClienteVip() {

	}

	public ClienteVip(String apellido, String nombre, String cedula, String genero, String tipoRegistro,
			BigDecimal valorIVA, BigDecimal valorTotal) {
		super();
		this.apellido = apellido;
		this.nombre = nombre;
		this.cedula = cedula;
		this.genero = genero;
		this.tipoRegistro = tipoRegistro;
		this.valorIVA = valorIVA;
		this.valorTotal = valorTotal;
	}

	// GET Y SET
	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	public BigDecimal getValorIVA() {
		return valorIVA;
	}

	public void setValorIVA(BigDecimal valorIVA) {
		this.valorIVA = valorIVA;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	@Override
	public String toString() {
		return "ClienteVIP [apellido=" + apellido + ", nombre=" + nombre + ", cedula=" + cedula + ", genero=" + genero
				+ ", tipoRegistro=" + tipoRegistro + ", valorIVA=" + valorIVA + ", valorTotal=" + valorTotal + "]";
	}

}