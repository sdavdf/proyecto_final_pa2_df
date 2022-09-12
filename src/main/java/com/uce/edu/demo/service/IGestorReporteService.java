package com.uce.edu.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import com.uce.edu.demo.repository.modelo.Reserva;
import com.uce.edu.demo.repository.modelo.Vehiculo;
import com.uce.edu.demo.repository.modelo.VehiculoVip;

public interface IGestorReporteService {
	
	public List<VehiculoVip> reporteVehiculosVip(Integer mes, Integer anio) ;
	
	public List<Reserva> buscarReservasVehiculoFecha(Vehiculo vechiculo, LocalDateTime fechaInicio,
			LocalDateTime FechaFin) ;

}