package com.uce.edu.demo.repository;

import java.util.List;

import com.uce.edu.demo.repository.modelo.Cliente;

public interface IClienteRepository {
	
	public void insertar(Cliente c);
	
	public Cliente buscar(Integer id);
	
	public void actualizar(Cliente c);
	
	public void eliminar(Integer id);
	
	public Cliente buscarPorCedula(String cedula);
	
	public List<Cliente> buscarTodosClientes();
	
	public List<Cliente> buscarClientesPorApellido(String apellido);
	
	public boolean verificarReserva(Integer id);

}
