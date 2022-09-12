package com.uce.edu.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import com.uce.edu.demo.repository.modelo.Reserva;
import com.uce.edu.demo.repository.modelo.Vehiculo;
import com.uce.edu.demo.repository.modelo.VehiculoBuscar;
import com.uce.edu.demo.repository.modelo.VehiculoVip;

public interface IVehiculoService {

	public void insertar(Vehiculo vehiculo);

	public Vehiculo buscar(Integer id);

	public void actualizar(Vehiculo vehiculo);

	public void borrar(Integer id);

	public Vehiculo buscarPorPlaca(String placa);

	public List<VehiculoBuscar> buscarMarcaModelo(String marca, String modelo);

	public List<Vehiculo> buscarMarca(String marca);

	public List<Vehiculo> buscarTodosVehiculos();

	public boolean verificarReserva(Integer id);

	public List<Vehiculo> buscarFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin);

	public List<VehiculoVip> reporteVehiculosVip(Integer mes, Integer anio);

	public List<Reserva> buscarReservasVehiculoFecha(Vehiculo vechiculo, LocalDateTime fechaInicio,
			LocalDateTime FechaFin);
}