package com.uce.edu.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.modelo.Reserva;
import com.uce.edu.demo.repository.modelo.Vehiculo;

@Service
public class GestorEmpleadoServiceImpl implements IGestorEmpleadoService {
	
	@Autowired
	private IReservaService ireservaService;
	
	@Autowired
	private IVehiculoService ivehiculoService;

	@Override
	public Reserva retirarVehiculoReservado(String numeroReserva) {
		// TODO Auto-generated method stub
				Reserva reserva = this.ireservaService.buscarNumero(numeroReserva);
				reserva.setEstado("E"); 
				this.ireservaService.actualizar(reserva);

//				Vehiculo vehiculo = reserva.getVehiculo();
//				vehiculo.setEstado("ND"); 
//				this.ivehiculoService.actualizar(vehiculo);
				return reserva;
			}

}
