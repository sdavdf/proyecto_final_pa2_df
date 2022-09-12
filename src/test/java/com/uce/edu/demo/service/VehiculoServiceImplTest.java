package com.uce.edu.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.uce.edu.demo.repository.modelo.Vehiculo;
import com.uce.edu.demo.repository.modelo.VehiculoBuscar;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional
public class VehiculoServiceImplTest {

	private static final Logger log = Logger.getRootLogger();

	@Autowired
	private IVehiculoService iVehiculoService;

	@Test
	@Rollback(true)
	public void insertarTest() {
		Vehiculo v = new Vehiculo();
		v.setValorPorDia(new BigDecimal(20));
		v.setPlaca("PCQ2223");
		v.setPaisFabricacion("Estados Unidos");
		v.setModelo("Deportivo");
		v.setMarca("Chevrolet");
		v.setFechaDisponibilidad(LocalDateTime.now());
		v.setEstado("DIS");
		v.setCilindraje("2000cc");
		v.setAvaluo(new BigDecimal(200));
		v.setAnioFabricacion("2022");

		this.iVehiculoService.insertar(v);

		assertNotNull(this.iVehiculoService.buscar(v.getId()));

	}

	@Test
	@Rollback(true)
	public void buscarTest() {
		Vehiculo v = new Vehiculo();
		v.setEstado("nuevo");
		this.iVehiculoService.insertar(v);
		Vehiculo vehi = this.iVehiculoService.buscar(v.getId());
		// log.info("vehiculo: " + vehi.getEstado());
		assertNotNull(vehi);

	}

	@Test
	@Rollback(true)
	public void actualizarTest() {
		Vehiculo v = new Vehiculo();
		v.setPlaca("21323");
		this.iVehiculoService.insertar(v);
		Vehiculo busqueda = this.iVehiculoService.buscar(v.getId());

		busqueda.setPlaca("99982");
		this.iVehiculoService.actualizar(busqueda);

		assertNotEquals(busqueda.getPlaca(), "21323");

	}

	@Test
	@Rollback(true)
	public void borrarTest() {
		// Vehiculo vehi = this.iVehiculoService.buscarPorPlaca("DJK-1741");

		Vehiculo ve = new Vehiculo();
		ve.setEstado("Nuevo");
		this.iVehiculoService.insertar(ve);

		Vehiculo comp = this.iVehiculoService.buscar(ve.getId());

		this.iVehiculoService.borrar(comp.getId());

		// log.info("vehiculo eliminado: " +
		// this.iVehiculoService.buscarPorPlaca("DJK-1741") );

		assertNull(this.iVehiculoService.buscar(comp.getId()));

	}

	@Test
	@Rollback(true)
	public void buscarPlaca() {

		Vehiculo v = new Vehiculo();
		v.setPlaca("324234");
		this.iVehiculoService.insertar(v);

		Vehiculo vehiPlac = this.iVehiculoService.buscarPorPlaca("324234");

		log.info("el dato es: " + vehiPlac);
		assertThat(vehiPlac.getId()).isNotNull();

	}

	@Test
	@Rollback(true)
	public void buscarMarcaModelo() {

		Vehiculo v = new Vehiculo();
		v.setModelo("Prius");
		v.setMarca("Toyota");
		this.iVehiculoService.insertar(v);
		assertThat(this.iVehiculoService.buscarMarcaModelo(v.getMarca(), v.getModelo())).isNotEmpty();

	}

	@Test
	@Rollback(true)
	public void buscarMarcaTest() {
		List<VehiculoBuscar> listaMarca = this.iVehiculoService.buscarMarcaModelo("Toyota", "Prius");

		if(listaMarca.isEmpty()) {
			assertThat(listaMarca).isEmpty();
		}else {
			assertThat(listaMarca).isNotEmpty();
		}
		
		assertNotNull(listaMarca);
		

	}

	@Test
	@Rollback(true)
	public void buscarTodosTest() {
		
		List<Vehiculo> busqueda = this.iVehiculoService.buscarTodosVehiculos();
		if(busqueda.isEmpty()) {
			assertThat(busqueda).isEmpty();
		}else {
			assertThat(busqueda).isNotEmpty();
		}
		
		

	}

	@Test
	@Rollback(true)
	public void insertarTest2() {
		Vehiculo v = new Vehiculo();
		v.setModelo("Ferrari");
		v.setMarca("Spider");

		this.iVehiculoService.insertar(v);

		assertNotNull(this.iVehiculoService.buscar(v.getId()));

	}

	@Test
	@Rollback(true)
	public void buscarTest2() {
		Vehiculo v = new Vehiculo();
		v.setEstado("usado");
		this.iVehiculoService.insertar(v);
		Vehiculo vehi = this.iVehiculoService.buscar(v.getId());
		// log.info("vehiculo: " + vehi.getEstado());
		assertNotNull(vehi.getEstado());
		assertNull(vehi.getAvaluo());
	}

	@Test
	@Rollback(true)
	public void actualizarTest2() {
		Vehiculo v = new Vehiculo();
		v.setCilindraje("3000cc");
		this.iVehiculoService.insertar(v);
		Vehiculo busqueda = this.iVehiculoService.buscar(v.getId());

		busqueda.setCilindraje("1000cc");
		this.iVehiculoService.actualizar(busqueda);

		assertNotEquals(busqueda.getPlaca(), "3000cc");

	}

	@Test
	@Rollback(true)
	public void borrarTest2() {

		Vehiculo ve = new Vehiculo();
		ve.setMarca("Ferrari");
		ve.setModelo("Spider");
		this.iVehiculoService.insertar(ve);

		Vehiculo comp = this.iVehiculoService.buscar(ve.getId());

		this.iVehiculoService.borrar(comp.getId());

		assertNull(this.iVehiculoService.buscar(comp.getId()));

	}

	@Test
	@Rollback(true)
	public void buscarPlaca2() {
		
	Vehiculo v = new Vehiculo();
	v.setPlaca("sdsadsdaf");
	this.iVehiculoService.insertar(v);
		Vehiculo vehiPlac = this.iVehiculoService.buscarPorPlaca("sdsadsdaf");
		log.info("El id del vehiculo con placa " + vehiPlac.getPlaca() + " es " + vehiPlac.getId());
		assertThat(vehiPlac.getId()).isNotNull();
	}

	@Test
	@Rollback(true)
	public void buscarMarcaModelo2() {

		assertThat(this.iVehiculoService.buscarMarcaModelo(null, null)).isEmpty();

	}

	@Test
	@Rollback(true)
	public void buscarMarcaTest2() {
		
		List<VehiculoBuscar> listaMarca2 = this.iVehiculoService.buscarMarcaModelo("Toyota", "Prius");

		if(listaMarca2.isEmpty()) {
			assertThat(listaMarca2).isEmpty();
		}else {
			assertThat(listaMarca2).isNotEmpty();
		}
		
		assertNotNull(listaMarca2);

	}

	@Test
	@Rollback(true)
	public void buscarTodosTest2() {
		

		Vehiculo v = new Vehiculo();
		v.setEstado("OCU");
		v.setMarca("Hyundai");
		this.iVehiculoService.insertar(v);
		Vehiculo v1 = new Vehiculo();
		v1.setEstado("OCU");
		v1.setMarca("Mercedes");

		this.iVehiculoService.insertar(v1);
		List<Vehiculo> busqueda = this.iVehiculoService.buscarTodosVehiculos();
		log.info("Los vehiculos son:" + busqueda);

		assertThat(busqueda).isNotEmpty();

	}

}
