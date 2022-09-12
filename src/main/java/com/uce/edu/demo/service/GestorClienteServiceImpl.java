package com.uce.edu.demo.service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.modelo.Cliente;
import com.uce.edu.demo.repository.modelo.Cobro;
import com.uce.edu.demo.repository.modelo.Reserva;
import com.uce.edu.demo.repository.modelo.Vehiculo;
import com.uce.edu.demo.repository.modelo.VehiculoVip;

@Service
public class GestorClienteServiceImpl implements IGestorClienteService {

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IVehiculoService vehiculoService;

	@Autowired
	private IReservaService ireservaService;

	@Override
	@Transactional
	public BigDecimal calcularPagoVehiculo(String placa, String cedula, LocalDateTime fechaInicio,
			LocalDateTime fechaFinal) {
		Vehiculo vehiculo = this.vehiculoService.buscarPorPlaca(placa);
		Duration duracion = Duration.between(fechaInicio, fechaFinal);
		long diasi = duracion.toDays();
		long dias = diasi + 1;

		Cliente cliente = this.clienteService.buscarPorCedula(cedula);

		// LOG.info("vehiculo disponible");
		BigDecimal valorsubTotal = vehiculo.getValorPorDia().multiply(new BigDecimal(dias));
		BigDecimal valorIVA = valorsubTotal.multiply(new BigDecimal(0.12));
		BigDecimal valorTotal = valorsubTotal.add(valorIVA);
		
		return valorTotal;
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public boolean verFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin, LocalDateTime fechaInicio2,
			LocalDateTime fechaFin2) {
		if (fechaInicio.isEqual(fechaInicio2)) {
			return true;
		} else if (fechaInicio2.isAfter(fechaInicio) && fechaInicio2.isBefore(fechaFin)) {
			return true;
		} else if (fechaFin2.isAfter(fechaInicio) && fechaFin2.isBefore(fechaFin)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Reserva reservarVehiculo(String placa, String cedula, LocalDateTime fechaInicio, LocalDateTime fechaFinal,
			String numeroTarjeta) {

		Vehiculo vehiculo = this.vehiculoService.buscarPorPlaca(placa);
		Duration duracion = Duration.between(fechaInicio, fechaFinal);
		long diasi = duracion.toDays();
		long dias = diasi + 1;

		Cliente cliente = this.clienteService.buscarPorCedula(cedula);

		BigDecimal valorsubTotal = vehiculo.getValorPorDia().multiply(new BigDecimal(dias));
		BigDecimal valorIVA = valorsubTotal.multiply(new BigDecimal(0.12));
		BigDecimal valorTotal = valorsubTotal.add(valorIVA);

		List<Reserva> reservasCliente = cliente.getReserva();
		if (reservasCliente == null) {
			reservasCliente = new ArrayList<>();
		}
		Reserva reserva = new Reserva();
		reserva.setCliente(cliente);
		reserva.setEstado("G");
		reserva.setFechaFin(fechaFinal);
		reserva.setFechaInicio(fechaInicio);
		reserva.setVehiculo(vehiculo);
		this.ireservaService.insertar(reserva);

		List<Reserva> reservaVehiculo = vehiculo.getReservas();
		if (reservaVehiculo == null) {
			reservaVehiculo = new ArrayList<>();
		}
		reservaVehiculo.add(reserva);

		vehiculo.setReservas(reservaVehiculo);
		vehiculo.setFechaDisponibilidad(fechaFinal);

		this.vehiculoService.actualizar(vehiculo);

		reservasCliente.add(reserva);
		cliente.setReserva(reservasCliente);
		this.clienteService.actualizar(cliente);

		Cobro cobro = new Cobro();
		cobro.setFecha(LocalDateTime.now());
		cobro.setReserva(reserva);
		cobro.setTarjeta(numeroTarjeta);
		cobro.setValorIVA(valorIVA);
		cobro.setValorSubtotal(valorsubTotal);
		cobro.setValorTotalPagar(valorTotal);

		reserva.setCobro(cobro);

		reserva.setNumero("000" + reserva.getId());
		this.ireservaService.actualizar(reserva);

		return reserva;
	}
	
	
	
}