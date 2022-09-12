package com.uce.edu.demo.service;

import com.uce.edu.demo.repository.modelo.Empleado;

public interface IEmpleadoService {
	
	public void insertar(Empleado e);

	public Empleado buscar(Integer id);

	public void actualizar(Empleado e);

	public void eliminar(Integer id);

}
