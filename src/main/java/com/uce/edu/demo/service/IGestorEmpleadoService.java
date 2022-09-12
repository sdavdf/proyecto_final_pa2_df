package com.uce.edu.demo.service;

import com.uce.edu.demo.repository.modelo.Reserva;

public interface IGestorEmpleadoService {
	
	public Reserva retirarVehiculoReservado(String numeroReserva);

}
