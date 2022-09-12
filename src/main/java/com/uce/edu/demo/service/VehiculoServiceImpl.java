package com.uce.edu.demo.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.IVehiculoRepository;
import com.uce.edu.demo.repository.modelo.Reserva;
import com.uce.edu.demo.repository.modelo.Vehiculo;
import com.uce.edu.demo.repository.modelo.VehiculoBuscar;
import com.uce.edu.demo.repository.modelo.VehiculoVip;

@Service
public class VehiculoServiceImpl implements IVehiculoService {

	@Autowired
	private IVehiculoRepository ivehiculoRepository;

	@Override
	public void insertar(Vehiculo vehiculo) {
		this.ivehiculoRepository.insertar(vehiculo);

	}

	@Override
	public Vehiculo buscar(Integer id) {
		return this.ivehiculoRepository.buscar(id);
	}

	@Override
	public void actualizar(Vehiculo vehiculo) {
		this.ivehiculoRepository.actualizar(vehiculo);

	}

	@Override
	public void borrar(Integer id) {
		this.ivehiculoRepository.borrar(id);

	}

	@Override
	public Vehiculo buscarPorPlaca(String placa) {

		return this.ivehiculoRepository.buscarPorPlaca(placa);
	}

	@Override
	public List<VehiculoBuscar> buscarMarcaModelo(String marca, String modelo) {

		return this.ivehiculoRepository.buscarMarcaModelo(marca, modelo);
	}

	@Override
	public List<Vehiculo> buscarTodosVehiculos() {
		// TODO Auto-generated method stub
		return this.ivehiculoRepository.buscarTodosVehiculos();
	}

	@Override
	public List<Vehiculo> buscarMarca(String marca) {
		// TODO Auto-generated method stub
		return this.ivehiculoRepository.buscarMarca(marca);
	}

	@Override
	public boolean verificarReserva(Integer id) {
		// TODO Auto-generated method stub
		return this.ivehiculoRepository.verificarReserva(id);
	}

	@Override
	public List<Vehiculo> buscarFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {

		return this.ivehiculoRepository.buscarFechas(fechaInicio, fechaFin);
	}

	@Override
	public List<VehiculoVip> reporteVehiculosVip(Integer mes, Integer anio) {
		LocalDateTime fechaInicio = LocalDateTime.of(anio, mes, 1, 0, 0);
		LocalDateTime fechaFin;
		if (mes == 2) {
			fechaFin = LocalDateTime.of(anio, mes, 28, 0, 0);
		} else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
			fechaFin = LocalDateTime.of(anio, mes, 30, 0, 0);
		} else {
			fechaFin = LocalDateTime.of(anio, mes, 31, 0, 0);
		}
		List<Vehiculo> vehiculosEnFechaIngresada = this.buscarFechas(fechaInicio, fechaFin);
		List<VehiculoVip> vehiculosVIP = new ArrayList<>();

		for (Vehiculo v : vehiculosEnFechaIngresada) {
			BigDecimal valorSubtotal = new BigDecimal(0);
			BigDecimal valorTotal = new BigDecimal(0);
			List<Reserva> reservasVehiculo = this.buscarReservasVehiculoFecha(v, fechaInicio, fechaFin);
			for (Reserva r : reservasVehiculo) {
				valorSubtotal = valorSubtotal.add(r.getCobro().getValorSubtotal());
				valorTotal = valorTotal.add(r.getCobro().getValorTotalPagar());
			}
			VehiculoVip vehiculoVIP = new VehiculoVip(v.getPlaca(), v.getModelo(), v.getMarca(), v.getAnioFabricacion(),
					v.getValorPorDia(), valorSubtotal, valorTotal);
			vehiculosVIP.add(vehiculoVIP);
		}

		List<VehiculoVip> listaOrdenada = vehiculosVIP.parallelStream()
				.sorted(Comparator.comparing(VehiculoVip::getValorTotal)).collect(Collectors.toList());

		listaOrdenada = listaOrdenada.stream()
				.sorted(Collections.reverseOrder(Comparator.comparing(VehiculoVip::getValorTotal)))
				.collect(Collectors.toList());
		return listaOrdenada;

	}

	@Override
	public List<Reserva> buscarReservasVehiculoFecha(Vehiculo vechiculo, LocalDateTime fechaInicio,
			LocalDateTime FechaFin) {
		List<Reserva> reservasVehiculo = vechiculo.getReservas();
		List<Reserva> reservasEnLasFechas = new ArrayList<>();
		for (Reserva r : reservasVehiculo) {
			if (r.getFechaInicio().isAfter(fechaInicio) && r.getFechaFin().isBefore(FechaFin)) {
				reservasEnLasFechas.add(r);
			}
		}

		return reservasEnLasFechas;

	}
}
