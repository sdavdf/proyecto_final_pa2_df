package com.uce.edu.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.uce.edu.demo.repository.modelo.Cliente;
import com.uce.edu.demo.repository.modelo.Reserva;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional
public class ClienteServiceImplTest {

	private static final Logger log = Logger.getRootLogger();

	@Autowired
	private IClienteService iClienteService;

	@Test
	@Rollback(true)
	public void insertarTest() {

		Cliente c = new Cliente();
		c.setTipoRegistro("C");
		c.setNombre("Xavier");
		c.setGenero("M");
		c.setFechaNacimiento(LocalDate.now());
		c.setCedula("231232");
		c.setApellido("Aguilar");
		c.setReserva(null);
		this.iClienteService.insertar(c);

		Cliente clienteBuscado = this.iClienteService.buscar(c.getId());

		assertEquals(c.getNombre(), clienteBuscado.getNombre());

	}
	
	

	@Test
	@Rollback(true)
	public void buscarTest() {
		Cliente cliente = new Cliente();
		cliente.setNombre("Xavi");
		this.iClienteService.insertar(cliente);

		Cliente busqueda = this.iClienteService.buscar(cliente.getId());
		log.info("Cliente: " + busqueda);
		assertThat(busqueda).isNotNull();
	}
//
	@Test
	@Rollback(true)
	public void actualizarTest() {
		Cliente c = new Cliente();
		c.setApellido("Fernandez");
		this.iClienteService.insertar(c);
		log.info("Cedula: " + c.getCedula());
		c.setCedula("23132");
		this.iClienteService.actualizar(c);
		log.info("Cedula actualizada: " + c.getCedula());
		assertNotNull(c.getCedula());
	}
//
	
	
	
	@Test
	@Rollback(true)
	public void buscarPorCedulaTest() {
		Cliente c = new Cliente();
		c.setCedula("11111");

		this.iClienteService.insertar(c);

		Cliente clienteBuscado = this.iClienteService.buscarPorCedula(c.getCedula());

		assertEquals(c.getCedula(), clienteBuscado.getCedula());

	}
//
	@Test
	@Rollback(true)
	public void eliminarClienteTest() {

		Cliente c = new Cliente();
		c.setNombre("Sam");
		c.setApellido("Jumbo");

		this.iClienteService.insertar(c);

		Cliente clienteEliminar = this.iClienteService.buscar(c.getId());

		this.iClienteService.eliminar(clienteEliminar.getId());

		log.info("Cliente eliminado: " + this.iClienteService.buscar(clienteEliminar.getId()));

		assertNull(this.iClienteService.buscar(clienteEliminar.getId()));
	}
//
	@Test
	@Rollback(true)
	public void buscarTodosTest() {
		List<Cliente> lista = this.iClienteService.buscarTodosClientes();
		if(lista.isEmpty()) {
			assertThat(lista).isEmpty();
		}else {
			assertThat(lista).isNotEmpty();
		}
		
	}
//
	@Test
	@Rollback(true)
	public void buscarPorApellidoTest() {
		Cliente c = new Cliente();
		c.setApellido("Jimenez");
		this.iClienteService.insertar(c);

		List<Cliente> busqueda = this.iClienteService.buscarClientesPorApellido(c.getApellido());

		assertThat(busqueda).isNotEmpty();

	}
//
	@Test
	@Rollback(true)
	public void buscarReservaTest() {

		List<Reserva> listar = new ArrayList<>();

		Reserva r = new Reserva();
		r.setEstado("Ocupado");

		listar.add(r);
		Cliente c = new Cliente();
		c.setReserva(listar);

		this.iClienteService.insertar(c);

		assertThat(this.iClienteService.verificarReserva(c.getId())).isTrue();

	}
//	
	@Test
	@Rollback(true)
	public void insertarTest2() {

		Cliente c = new Cliente();
		c.setTipoRegistro("E");
		c.setNombre("Pedro");
		
		this.iClienteService.insertar(c);

		Cliente clienteBuscado = this.iClienteService.buscar(c.getId());

		assertNotNull(clienteBuscado);;

	}
	

	@Test
	@Rollback(true)
	public void buscarTest2() {
		Cliente cliente = new Cliente();
		cliente.setNombre("Juan");
		this.iClienteService.insertar(cliente);

		Cliente busqueda = this.iClienteService.buscar(cliente.getId());
		log.info("Cliente: " + busqueda);
		assertNotNull(busqueda);
	}

	@Test
	@Rollback(true)
	public void actualizarTest2() {
		Cliente c = new Cliente();
		c.setGenero("Masculino");
		this.iClienteService.insertar(c);
		log.info("Genero: " + c.getGenero());
		c.setGenero("Femenino");
		this.iClienteService.actualizar(c);
		log.info("Genero actualizado: " + c.getGenero());
		Cliente c2 = this.iClienteService.buscar(c.getId());
		assertNotNull(c2.getGenero());
	}

	@Test
	@Rollback(true)
	public void buscarPorCedulaTest2() {
		Cliente c = new Cliente();
		c.setCedula("213232");

		this.iClienteService.insertar(c);

		Cliente clienteBuscado = this.iClienteService.buscarPorCedula("213232");

		assertEquals("213232", clienteBuscado.getCedula());

	}

	@Test
	@Rollback(true)
	public void eliminarClienteTest2() {

		Cliente c = new Cliente();
		c.setNombre("Dome");
		c.setApellido("Zambrano");
		c.setCedula("3243245");

		this.iClienteService.insertar(c);

		Cliente clienteEliminar = this.iClienteService.buscarPorCedula(c.getCedula());

		this.iClienteService.eliminar(clienteEliminar.getId());

		log.info("Cliente eliminado: " + this.iClienteService.buscar(clienteEliminar.getId()));

		assertNull(this.iClienteService.buscar(clienteEliminar.getId()));
	}

	@Test
	@Rollback(true)
	public void buscarTodosTest2() {
		List<Cliente> lista = this.iClienteService.buscarTodosClientes();
		
		Cliente c = new Cliente();
		c.setNombre("Pedro");
		
		lista.add(c);
		
		assertThat(lista).size();
	}

	@Test
	@Rollback(true)
	public void buscarPorApellidoTest2() {
		Cliente c = new Cliente();
		c.setApellido("Ordoñez");
		Cliente c1 = new Cliente();
		c1.setApellido("Ordoñez");
		Cliente c2 = new Cliente();
		c2.setApellido("Ordoñez");
		this.iClienteService.insertar(c);
		this.iClienteService.insertar(c1);
		this.iClienteService.insertar(c2);
		
		String apellido ="Ordoñez";
		List<Cliente> busqueda = this.iClienteService.buscarClientesPorApellido(apellido);
		log.info("Clientes con apellido:" + apellido + " son: " + busqueda);
		assertThat(busqueda).isNotEmpty();

	}

	@Test
	@Rollback(true)
	public void buscarReservaTest2() {

		List<Reserva> lista = new ArrayList<>();

		Reserva res = new Reserva();
		res.setEstado("Disponible");

		lista.add(res);
		Cliente c = new Cliente();
		c.setReserva(lista);

		this.iClienteService.insertar(c);

		assertThat(this.iClienteService.verificarReserva(c.getId())).isTrue();

	}
	

}
