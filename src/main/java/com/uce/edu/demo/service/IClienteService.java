package com.uce.edu.demo.service;

import java.util.List;

import com.uce.edu.demo.repository.modelo.Cliente;
import com.uce.edu.demo.repository.modelo.ClienteVip;

public interface IClienteService {
	
public void insertar(Cliente c);
	
	public Cliente buscar(Integer id);
	
	public void actualizar(Cliente c);
	
	public void eliminar(Integer id);
	
	public Cliente buscarPorCedula(String cedula);
	
	public List<Cliente> buscarTodosClientes();
	
	public List<Cliente> buscarClientesPorApellido(String apellido);
	
	public boolean verificarReserva(Integer id);
	
	public List<ClienteVip> reporteClientesVip();
	

}
