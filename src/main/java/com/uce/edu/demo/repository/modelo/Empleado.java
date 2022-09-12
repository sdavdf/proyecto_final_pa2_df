package com.uce.edu.demo.repository.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "empleado")
public class Empleado {

	@Id
	@Column(name = "empl_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_empleado")
	@SequenceGenerator(name = "seq_empleado", sequenceName = "seq_empleado", allocationSize = 1)
	private Integer id;

	@Column(name = "empl_apellido")
	private String apellido;

	@Column(name = "empl_nombre")
	private String nombre;

	@Column(name = "empl_cedula")
	private String cedula;

	// GET Y SET
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	@Override
	public String toString() {
		return "Empleado [id=" + id + ", apellido=" + apellido + ", nombre=" + nombre + ", cedula=" + cedula + "]";
	}

}
