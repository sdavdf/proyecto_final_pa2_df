package com.uce.edu.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.IEmpleadoRepository;
import com.uce.edu.demo.repository.modelo.Empleado;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService {
	
	@Autowired
	private IEmpleadoRepository iempleadoRepository;

	@Override
	public void insertar(Empleado e) {
		this.iempleadoRepository.insertar(e);
		
	}

	@Override
	public Empleado buscar(Integer id) {
		return this.iempleadoRepository.buscar(id);
	}

	@Override
	public void actualizar(Empleado e) {
		this.iempleadoRepository.actualizar(e);
		
	}

	@Override
	public void eliminar(Integer id) {
		this.iempleadoRepository.eliminar(id);
		
	}

}
