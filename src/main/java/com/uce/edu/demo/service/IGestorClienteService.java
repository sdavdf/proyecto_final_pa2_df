package com.uce.edu.demo.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.uce.edu.demo.repository.modelo.Reserva;
import com.uce.edu.demo.repository.modelo.VehiculoVip;

public interface IGestorClienteService {

	public BigDecimal calcularPagoVehiculo(String placa, String cedula, LocalDateTime fechaInicio,
			LocalDateTime fechaFinal);

	public boolean verFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin, LocalDateTime fechaInicio2,
			LocalDateTime fechaFin2);
	
	public Reserva reservarVehiculo(String placa, String cedula, LocalDateTime fechaInicio, LocalDateTime fechaFinal,
			String numeroTarjeta);
	


}