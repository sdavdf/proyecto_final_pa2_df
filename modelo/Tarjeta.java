package com.uce.edu.demo.repository.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//@Entity
//@Table(name = "tarjeta")
public class Tarjeta {

	@Id
	@Column(name = "tarj_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tarj")
	@SequenceGenerator(name = "seq_tarj", sequenceName = "seq_tarj", allocationSize = 1)
	private Integer id;

	@Column(name = "tarj_numero")
	private String numero;

	@Column(name = "tarj_marca")
	private String marca;

	@OneToOne(mappedBy = "tarjeta")
	private Cobro cobro;

	//GET Y SET 
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Cobro getCobro() {
		return cobro;
	}

	public void setCobro(Cobro cobro) {
		this.cobro = cobro;
	}

	@Override
	public String toString() {
		return "Tarjeta [id=" + id + ", numero=" + numero + ", marca=" + marca + "]";
	}

}