package com.uce.edu.demo.repository;

import com.uce.edu.demo.repository.modelo.Empleado;

public interface IEmpleadoRepository {

	public void insertar(Empleado e);

	public Empleado buscar(Integer id);

	public void actualizar(Empleado e);

	public void eliminar(Integer id);
}
