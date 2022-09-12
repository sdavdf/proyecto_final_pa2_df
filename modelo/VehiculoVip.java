package com.uce.edu.demo.repository.modelo;

import java.math.BigDecimal;

public class VehiculoVip {
	private String placa;
	private String modelo;
	private String marca;
	private String anioFabricacion;
	private BigDecimal valorPorDia;
	private BigDecimal valorSubTotal;
	private BigDecimal valorTotal;

	public VehiculoVip() {

	}

	public VehiculoVip(String placa, String modelo, String marca, String anioFabricacion, BigDecimal valorPorDia,
			BigDecimal valorSubTotal, BigDecimal valorTotal) {
		super();
		this.placa = placa;
		this.modelo = modelo;
		this.marca = marca;
		this.anioFabricacion = anioFabricacion;
		this.valorPorDia = valorPorDia;
		this.valorSubTotal = valorSubTotal;
		this.valorTotal = valorTotal;
	}
	// GET Y SET

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

	public BigDecimal getValorPorDia() {
		return valorPorDia;
	}

	public void setValorPorDia(BigDecimal valorPorDia) {
		this.valorPorDia = valorPorDia;
	}

	public BigDecimal getValorSubTotal() {
		return valorSubTotal;
	}

	public void setValorSubTotal(BigDecimal valorSubTotal) {
		this.valorSubTotal = valorSubTotal;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	@Override
	public String toString() {
		return "VehiculoVIP [placa=" + placa + ", modelo=" + modelo + ", marca=" + marca + ", anioFabricacion="
				+ anioFabricacion + ", valorPorDia=" + valorPorDia + ", valorSubTotal=" + valorSubTotal
				+ ", valorTotal=" + valorTotal + "]";
	}

}