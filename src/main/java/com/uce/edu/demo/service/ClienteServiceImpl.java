package com.uce.edu.demo.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.IClienteRepository;
import com.uce.edu.demo.repository.modelo.Cliente;
import com.uce.edu.demo.repository.modelo.ClienteVip;
import com.uce.edu.demo.repository.modelo.Reserva;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteRepository iclienteRepository;

	@Override
	public void insertar(Cliente c) {
		this.iclienteRepository.insertar(c);

	}

	@Override
	public Cliente buscar(Integer id) {

		return this.iclienteRepository.buscar(id);
	}

	@Override
	public void actualizar(Cliente c) {
		this.iclienteRepository.actualizar(c);

	}

	@Override
	public void eliminar(Integer id) {
		this.iclienteRepository.eliminar(id);

	}

	@Override
	//@Transactional(value = TxType.NOT_SUPPORTED)
	public Cliente buscarPorCedula(String cedula) {

		return this.iclienteRepository.buscarPorCedula(cedula);
	}

	@Override
	public List<Cliente> buscarTodosClientes() {

		return this.iclienteRepository.buscarTodosClientes();
	}

	@Override
	public List<Cliente> buscarClientesPorApellido(String apellido) {
	
		return this.iclienteRepository.buscarClientesPorApellido(apellido);
	}

	@Override
	public boolean verificarReserva(Integer id) {
		
		return this.iclienteRepository.verificarReserva(id);
	}

	@Override
	public List<ClienteVip> reporteClientesVip() {
		List<Cliente> listaClientes = this.buscarTodosClientes();
		List<ClienteVip> clientesVIP = new ArrayList<>();
		
		for (Cliente c : listaClientes) {
			BigDecimal valorIVA = new BigDecimal(0);
			BigDecimal valorTotal= new BigDecimal(0);
			List<Reserva> reservasCliente = c.getReserva();
			for (Reserva r : reservasCliente) {
				valorIVA=valorIVA.add(r.getCobro().getValorIVA());
				valorTotal=valorTotal.add(r.getCobro().getValorTotalPagar());
				
			}
			ClienteVip clienteVIP = new ClienteVip(c.getApellido(), c.getNombre(), c.getCedula(), c.getGenero(),
					c.getTipoRegistro(), valorIVA, valorTotal);
			clientesVIP.add(clienteVIP);
		}
		
		List<ClienteVip> listaOrdenada=clientesVIP.parallelStream().sorted(Comparator.comparing(ClienteVip::getValorTotal)).collect(Collectors.toList());
		listaOrdenada=listaOrdenada.stream().sorted(Collections.reverseOrder(Comparator.comparing(ClienteVip::getValorTotal))).collect(Collectors.toList());
		return listaOrdenada;
	}

}