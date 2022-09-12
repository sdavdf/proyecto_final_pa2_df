package com.uce.edu.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.uce.edu.demo.repository.modelo.Empleado;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional
public class EmpleadoServiceImplTest {

	@Autowired
	private IEmpleadoService iEmpleadoService;

	@Test
	@Rollback(true)
	@Order(7)
	public void insertarTest() {
		Empleado e = new Empleado();
		e.setNombre("Xavier");
		e.setCedula("23213213");
		e.setApellido("Aguilar");

		this.iEmpleadoService.insertar(e);

		assertNotNull(this.iEmpleadoService.buscar(e.getId()));
	}

	@Test
	@Rollback(true)
	@Order(1)
	public void actualizarTest() {
		Empleado e = new Empleado();
		e.setNombre("Pablo");
		e.setCedula("87984321");
		e.setApellido("Argue");

		this.iEmpleadoService.insertar(e);

		Empleado nuevo = this.iEmpleadoService.buscar(e.getId());

		nuevo.setCedula("656461651");
		this.iEmpleadoService.actualizar(nuevo);

		assertNotEquals("87984321", nuevo.getCedula());

	}

	@Test
	@Rollback(true)
	@Order(6)
	public void buscarTest() {

		Empleado e = new Empleado();
		e.setNombre("Xavier");
		e.setCedula("23432432");
		e.setApellido("Aguilar");

		this.iEmpleadoService.insertar(e);

		Empleado busqueda = this.iEmpleadoService.buscar(e.getId());

		assertEquals(busqueda.getApellido(), e.getApellido());

	}

	@Test
	@Rollback(true)
	@Order(2)
	public void eliminarTest() {
		Empleado e = new Empleado();
		e.setNombre("Xavi");
		e.setApellido("agg");
		e.setCedula("3546546");

		this.iEmpleadoService.insertar(e);

		Empleado e1 = new Empleado();
		e1.setNombre("Pedro");
		e1.setApellido("Pablo");
		e1.setCedula("35445435");

		this.iEmpleadoService.insertar(e1);

		this.iEmpleadoService.eliminar(e.getId());

		assertNotEquals(e.getId(), e1.getId());

	}
	
	@Test
	@Rollback(true)
	@Order(8)
	public void insertarTest2() {
		Empleado e = new Empleado();
		e.setNombre("Pedro");

		this.iEmpleadoService.insertar(e);

		assertNotNull(this.iEmpleadoService.buscar(e.getId()));
	}

	@Test
	@Rollback(true)
	@Order(3)
	public void actualizarTest2() {
		Empleado e = new Empleado();
		
		e.setCedula("435345");
		

		this.iEmpleadoService.insertar(e);

		Empleado nuevo = this.iEmpleadoService.buscar(e.getId());

		nuevo.setCedula("656461651");
		this.iEmpleadoService.actualizar(nuevo);

		assertNotEquals("435345", nuevo.getCedula());

	}

	@Test
	@Rollback(true)
	@Order(5)
	public void buscarTest2() {

		Empleado e = new Empleado();
		e.setApellido("Peña");

		this.iEmpleadoService.insertar(e);

		Empleado busqueda = this.iEmpleadoService.buscar(e.getId());

		assertNotNull(busqueda);;

	}

	@Test
	@Rollback(true)
	@Order(4)
	public void eliminarTest2() {
		Empleado e = new Empleado();
		e.setCedula("657665");

		this.iEmpleadoService.insertar(e);

		Empleado e1 = new Empleado();
		e1.setCedula("657665");

		this.iEmpleadoService.insertar(e1);

		this.iEmpleadoService.eliminar(e.getId());

		assertNotEquals(e.getId(), e1.getId());

	}

}
