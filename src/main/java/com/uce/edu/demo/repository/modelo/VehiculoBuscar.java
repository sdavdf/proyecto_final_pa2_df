package com.uce.edu.demo.repository.modelo;

import java.math.BigDecimal;

//Objeto Sencillo
public class VehiculoBuscar {

	private String placa;
	private String modelo;
	private String marca;
	private String anioFabricacion;
	private String estado;
	private BigDecimal valorPorDia;

	public VehiculoBuscar() {

	}

	public VehiculoBuscar(String placa, String modelo, String marca, String anioFabricacion, String estado,
			BigDecimal valorPorDia) {
		
		this.placa = placa;
		this.modelo = modelo;
		this.marca = marca;
		this.anioFabricacion = anioFabricacion;
		this.estado = estado;
		this.valorPorDia = valorPorDia;
	}

	//GET Y SET
	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getAnioFabricacion() {
		return anioFabricacion;
	}

	public void setAnioFabricacion(String anioFabricacion) {
		this.anioFabricacion = anioFabricacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public BigDecimal getValorPorDia() {
		return valorPorDia;
	}

	public void setValorPorDia(BigDecimal valorPorDia) {
		this.valorPorDia = valorPorDia;
	}

	@Override
	public String toString() {
		return "Placa:" + placa + " - Modelo=" + modelo + " - Marca=" + marca + " - Anio de Fabricacion: "
				+ anioFabricacion + " - Estado=" + estado + "- Valor por dia: $" + valorPorDia;
	}

}