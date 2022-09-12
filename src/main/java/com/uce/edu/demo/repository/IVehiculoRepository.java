package com.uce.edu.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.uce.edu.demo.repository.modelo.Vehiculo;
import com.uce.edu.demo.repository.modelo.VehiculoBuscar;

public interface IVehiculoRepository {
	
	public void insertar(Vehiculo vehiculo);
	
	public Vehiculo buscar(Integer id);

	public void actualizar(Vehiculo vehiculo);

	public void borrar(Integer id);

	public List<VehiculoBuscar> buscarMarcaModelo(String marca, String modelo);
	public List<Vehiculo> buscarMarca(String marca);
	
	public List<Vehiculo> buscarTodosVehiculos();
	
	public Vehiculo buscarPorPlaca(String placa);
	
	public List<Vehiculo> buscarPorFechas(LocalDateTime fechaInicio, LocalDateTime fechaFinal);
	
	public boolean verificarReserva(Integer id);
	
	public List<Vehiculo> buscarFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin);


}
