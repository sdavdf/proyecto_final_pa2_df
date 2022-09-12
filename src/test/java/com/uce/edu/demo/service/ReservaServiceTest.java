package com.uce.edu.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.uce.edu.demo.repository.modelo.Cliente;
import com.uce.edu.demo.repository.modelo.Cobro;
import com.uce.edu.demo.repository.modelo.Reserva;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional
public class ReservaServiceTest {
	
	private static final Logger log = Logger.getRootLogger();
	
	@Autowired
	private IReservaService iReservaService;
	
	@Test
	@Rollback(true)
	public void insertarTest() {
		Reserva r = new Reserva();
		r.setNumero("432432");
		
		this.iReservaService.insertar(r);
		
		Reserva bus = this.iReservaService.buscar(r.getId());
		assertNotNull(bus);
	}
	
	@Test
	@Rollback(true)
	public void buscarTest() {
		Cliente c = new Cliente();
		c.setApellido("agg");
		
		Reserva res = new Reserva();
		res.setCliente(c);
		
		this.iReservaService.insertar(res);
		
		Reserva busqueda = this.iReservaService.buscar(res.getId());
		assertThat(busqueda).isNotNull();
	}
	
	@Test
	@Rollback(true)
	public void actualizarTest() {
		Reserva r = new Reserva();
		r.setEstado("DIS");
		this.iReservaService.insertar(r);
		log.info("Reserva: " + r.getEstado());
		r.setEstado("OCU");
		this.iReservaService.actualizar(r);
		log.info("Reserva actualizada: " + r.getEstado());
		assertNotNull( r.getEstado());
	}
	
	@Test
	@Rollback(true)
	public void eliminarTest() {
		Reserva r = new Reserva();
		r.setNumero("2323");

		this.iReservaService.insertar(r);

		Reserva reservaEliminar = this.iReservaService.buscar(r.getId());

		this.iReservaService.eliminar(reservaEliminar.getId());

		log.info("Reserva eliminado: " + this.iReservaService.buscar(reservaEliminar.getId()));

		assertNull(this.iReservaService.buscar(reservaEliminar.getId()));
	}
	
	@Test
	@Rollback(true)
	public void buscarNumeroTest() {
		Reserva r = new Reserva();
		r.setNumero("324234");
		this.iReservaService.insertar(r);
		
		Reserva res = this.iReservaService.buscarNumero(r.getNumero());
		assertNotNull(res);
	}
	
	@Test
	@Rollback(true)
	public void insertarTest2() {
		Reserva r = new Reserva();
		r.setFechaFin(LocalDateTime.now());
		
		this.iReservaService.insertar(r);
		
		Reserva bus = this.iReservaService.buscar(r.getId());
		
		assertThat(bus).isNotNull();
	}
	
	@Test
	@Rollback(true)
	public void buscarTest2() {
		Cobro c = new Cobro();
		c.setTarjeta("23234432");
		Reserva res = new Reserva();
		res.setCobro(c);
		
		this.iReservaService.insertar(res);
		
		Reserva busqueda = this.iReservaService.buscar(res.getId());
		assertThat(busqueda).isNotNull();
	}
	
	@Test
	@Rollback(true)
	public void actualizarTest2() {
		Reserva r = new Reserva();
		r.setFechaInicio(LocalDateTime.of(2022, 9, 10, 0, 0));
		this.iReservaService.insertar(r);
		log.info("Reserva: " + r.getFechaInicio());
		r.setFechaInicio(LocalDateTime.now());
		this.iReservaService.actualizar(r);
		log.info("Reserva actualizada: " + r.getFechaInicio());
		assertNotNull(  r.getFechaInicio());
	}
	
	@Test
	@Rollback(true)
	public void eliminarTest2() {
		Reserva r = new Reserva();
		r.setEstado("OCU");

		this.iReservaService.insertar(r);

		Reserva reservaEliminar = this.iReservaService.buscar(r.getId());

		this.iReservaService.eliminar(reservaEliminar.getId());

		log.info("Reserva eliminado: " + this.iReservaService.buscar(reservaEliminar.getId()));

		assertNull(this.iReservaService.buscar(reservaEliminar.getId()));
	}
	
	@Test
	@Rollback(true)
	public void buscarNumeroTest2() {
		Reserva r = new Reserva();
		r.setNumero("45346554");
		this.iReservaService.insertar(r);
		
		Reserva res = this.iReservaService.buscarNumero(r.getNumero());
		log.info("El numero de la reserva es: " + res);
		assertNotNull(res);
	}

}
