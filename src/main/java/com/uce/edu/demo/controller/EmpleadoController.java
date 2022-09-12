package com.uce.edu.demo.controller;

import java.math.BigDecimal;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uce.edu.demo.ProyectoGrupalApplication;
import com.uce.edu.demo.repository.modelo.Cliente;
import com.uce.edu.demo.repository.modelo.Cobro;
import com.uce.edu.demo.repository.modelo.Reserva;
import com.uce.edu.demo.repository.modelo.Vehiculo;
import com.uce.edu.demo.repository.modelo.VehiculoBuscar;
import com.uce.edu.demo.service.IClienteService;
import com.uce.edu.demo.service.IGestorClienteService;
import com.uce.edu.demo.service.IGestorEmpleadoService;
import com.uce.edu.demo.service.IReservaService;
import com.uce.edu.demo.service.IVehiculoService;

@Controller
@RequestMapping("/empleados/")
public class EmpleadoController {

	private static Logger LOG = Logger.getLogger(ProyectoGrupalApplication.class);

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IVehiculoService vehiculoService;

	@Autowired
	private IReservaService reservaService;
	@Autowired
	private IGestorEmpleadoService igestorEmpleadoService;

	@Autowired
	private IGestorClienteService igestorClienteService;

	////// Registrar Cliente //////////////
	/////////////// Principal
	@RequestMapping("index")
	public String obtenerPaginaPrincipal() {
		return "empleado/vistaIndexEmpleado";
	}

	// Vista para el registro del Cliente
	@GetMapping("clienteNuevo")
	public String obtenerPaginaIngresoDatos(Cliente cliente) {
		return "empleado/clienteNuevo";

	}

	// Registro el cliente y lo redirecciono a la vista de arriba
	@PostMapping("registrarCliente")
	public String registrarCliente(Cliente cliente, BindingResult resut, Model modelo,
			RedirectAttributes redirectAttributes) {
		cliente.setTipoRegistro("E");
		if (this.clienteService.buscarPorCedula(cliente.getCedula()) != null) {
			redirectAttributes.addFlashAttribute("mensaje", "Cedula ya existe").addFlashAttribute("clase", "danger");
		} else {
			this.clienteService.insertar(cliente);
			redirectAttributes.addFlashAttribute("mensaje", "Agregado correctamente").addFlashAttribute("clase",
					"success");
		}

		return "redirect:/empleados/clienteNuevo";
	}

	/////// (Buscar, eliminar, actualizar) cliente

	/////////////////////////////////////////////////////////////////////////
	@GetMapping("/editar/{clieCedula}")
	public String editarCliente(@PathVariable("clieCedula") String cedula, Cliente cliente, Model modelo) {
		Cliente clie = this.clienteService.buscarPorCedula(cedula);
		LOG.info(clie.toString());
		modelo.addAttribute("clie", clie);
		return "empleado/clieActualizar";
	}

	@PutMapping("/actualizar/{clieCedula}")
	public String actualizarCliente(@PathVariable("clieCedula") String cedula, Cliente clie,
			RedirectAttributes redirectAttrs) {
		Cliente cliente = this.clienteService.buscarPorCedula(clie.getCedula());
		clie.setId(cliente.getId());
		clie.setTipoRegistro(cliente.getTipoRegistro());
		clie.setReserva(cliente.getReserva());
		this.clienteService.actualizar(clie);
		redirectAttrs.addFlashAttribute("mensaje", "Cliente cedula: " + clie.getCedula() + " editado correctamente")
				.addFlashAttribute("clase", "success");
		return "redirect:/empleados/todos";
	}

	@GetMapping("/eliminar/{clieCedula}")
	public String eliminarCliente(@PathVariable("clieCedula") String cedula, Model modelo,
			RedirectAttributes redirectAttrs) {
		Cliente cliente = this.clienteService.buscarPorCedula(cedula);
		Boolean validar = this.clienteService.verificarReserva(cliente.getId());

		if (validar == false) {

			this.clienteService.eliminar(cliente.getId());

		} else {
			// modelo.addAttribute("validar", false);
			redirectAttrs.addFlashAttribute("mensaje", "No se puede eliminar, tiene reservas activas")
					.addFlashAttribute("clase", "danger");
		}

		return "redirect:/empleados/todos";
	}

	@GetMapping("/ver/{cedula}")
	public String verDatosCliente(@PathVariable("cedula") String cedula, Model modelo) {
		Cliente cliente = this.clienteService.buscarPorCedula(cedula);
		modelo.addAttribute("cliente", cliente);
		return "empleado/datosCliente";
	}

	@GetMapping("todos")
	public String listarClientes(Model modelo, @Param("apellido") String apellido) {
		List<Cliente> listaClientes = this.clienteService.buscarTodosClientes();
		List<Cliente> listaclientes1 = this.clienteService.buscarClientesPorApellido(apellido);
		modelo.addAttribute("clientes", listaclientes1);
		modelo.addAttribute("apellido", apellido);
		LOG.info("apellido: " + apellido);
		if (apellido == null) {
			modelo.addAttribute("clientes", listaClientes);
		}

		return "empleado/todosClientes";
	}
	// Vista Ingresar Vehiculo

	@GetMapping("vehiculoNuevo")
	public String obtenerPaginaIngresoVehiculo(Vehiculo vehiculo) {
		return "empleado/vehiculoNuevo";

	}

	@PostMapping("ingresarVehiculo")
	public String ingresarVehiculo(Vehiculo vehiculo, BindingResult resut, Model modelo,
			RedirectAttributes redirectAttributes) {
		
	if (this.vehiculoService.buscarPorPlaca(vehiculo.getPlaca() ) != null){
		redirectAttributes.addFlashAttribute("mensaje", "Placa ya existe").addFlashAttribute("clase", "danger");
		return "redirect:/empleados/vehiculoNuevo";

	} else {
			vehiculo.setEstado("D");
			this.vehiculoService.insertar(vehiculo);
			redirectAttributes.addFlashAttribute("mensaje", "Registrado correctamente").addFlashAttribute("clase", "success");
			return "redirect:/empleados/vehiculoNuevo";
		}
		
		
	}

/////// (Buscar, eliminar, actualizar) vehiculo 

	/////////////////////////////////////////////////////////////////////////
	@GetMapping("/editarVehiculo/{placa}")
	public String editarVehiculo(@PathVariable("placa") String placa, Vehiculo vehiculo, Model modelo) {
		Vehiculo vehi = this.vehiculoService.buscarPorPlaca(placa);
		// LOG.info(clie.toString());
		modelo.addAttribute("vehiculo", vehi);
		return "empleado/vehiActualizar";
	}

	@PutMapping("/actualizarVehiculo/{placa}")
	public String actualizarVehiculo(@PathVariable("placa") String placa, Vehiculo vehi,
			RedirectAttributes redirectAttrs) {
		Vehiculo vehiculo = this.vehiculoService.buscarPorPlaca(vehi.getPlaca());
		vehi.setId(vehiculo.getId());
		// clie.setTipoRegistro(cliente.getTipoRegistro());
		// clie.setReserva(cliente.getReserva());
		this.vehiculoService.actualizar(vehi);
//		redirectAttrs.addFlashAttribute("mensaje", "Cliente cedula: " + clie.getCedula() + " editado correctamente")
//				.addFlashAttribute("clase", "success");
		return "redirect:/empleados/todosVehiculo";
	}
	@PutMapping("/actualizarVehiculoEstado/{placa}")
	public String actualizarVehiculoEstado(@PathVariable("placa") String placa, Vehiculo vehi,
	RedirectAttributes redirectAttrs) {
		Vehiculo vehiculo = this.vehiculoService.buscarPorPlaca(vehi.getPlaca());
		vehi.setId(vehiculo.getId());
		vehiculo.setEstado("ND");
		this.vehiculoService.actualizar(vehiculo);
		return "redirect:/empleados/todosVehiculo";
	}

	@GetMapping("/eliminarVehiculo/{placa}")
	public String eliminarVehiculo(@PathVariable("placa") String placa, Model modelo,
			RedirectAttributes redirectAttrs) {
		Vehiculo vehiculo = this.vehiculoService.buscarPorPlaca(placa);
		Boolean validar = this.vehiculoService.verificarReserva(vehiculo.getId());

		if (validar == false) {

			this.vehiculoService.borrar(vehiculo.getId());

		} else {

			redirectAttrs.addFlashAttribute("mensaje2", "No se puede eliminar, tiene reservas activas")
					.addFlashAttribute("clase2", "danger");
		}

		return "redirect:/empleados/todosVehiculo";
	}

	@GetMapping("/verVehiculo/{placa}")
	public String verDatosVehiculo(@PathVariable("placa") String placa, Model modelo) {
		Vehiculo vehiculo = this.vehiculoService.buscarPorPlaca(placa);
		modelo.addAttribute("vehiculo", vehiculo);
		return "empleado/datosVehiculo";
	}

	@GetMapping("todosVehiculo")
	public String listarVehiculo(Model modelo, @Param("marca") String marca) {
		List<Vehiculo> listaClientes = this.vehiculoService.buscarTodosVehiculos();
		List<Vehiculo> listaclientes1 = this.vehiculoService.buscarMarca(marca);
		modelo.addAttribute("vehiculos", listaclientes1);
		modelo.addAttribute("marca", marca);
		LOG.info("marca: " + marca);
		if (marca == null) {
			modelo.addAttribute("vehiculos", listaClientes);
		}

		return "empleado/todosVehiculos";
	}

	/////// Retirar Vehiculo Reservado

	/////////////////////////////////////////////////////////////////////////

	@GetMapping("retirarVehiculoBuscar")
	public String obtenerPaginaRetirarVehiculo(Reserva reserva) {
		return "empleado/retirarVehiculo";

	}

	@GetMapping("retirarVehiculoReservado")
	public String retirarVehiculo(Model modelo, Reserva reserva,RedirectAttributes redirectAttributes) {
	
		if(this.reservaService.buscarNumero(reserva.getNumero())==null) {
			redirectAttributes.addFlashAttribute("mensaje", "No existe la reserva")
			.addFlashAttribute("clase", "danger");
			return "redirect:/empleados/retirarVehiculoBuscar";
		}
		else {
			Reserva reservaR = this.igestorEmpleadoService.retirarVehiculoReservado(reserva.getNumero());
			
			modelo.addAttribute("reservaR", reservaR);
			return "empleado/retirarVehiculoMostrar";
		}
		

	}

	// ********** retirar vehiculo sin reserva

	// primer metodo para buscar vehiculos disponibles
	@GetMapping("buscarVehiculos")
	public String obtenerPaginaIngresoMarcaModelo(Vehiculo vehiculo) {
		return "empleado/buscarVehiculoDispS";

	}

	// segundo metodo para buscar vehiculos disponibles
	@GetMapping("buscar/disponibles")
	public String mostrarVehiculosDisponibles(Vehiculo vehiculo, Model modelom) {
		List<VehiculoBuscar> vehiculosBuscados = this.vehiculoService.buscarMarcaModelo(vehiculo.getMarca(),
				vehiculo.getModelo());
		modelom.addAttribute("vehiculos", vehiculosBuscados);
		return "empleado/listaVehiculosDisponiblesS";

	}

	// primer metodo para reservar vehiculo
	@GetMapping("reservar/buscarVehiculo")
	public String obtenerPaginaBuscarVehiculo(Reserva reserva, Model modelo) {
		modelo.addAttribute("reserva", reserva);
		return "empleado/reservarBuscarVehiculoS";

	}

	// segundo metodo para reservar vehiculo
	@GetMapping("verificarVehiculo")
	public String verificarVehiculo(Model modelo, Reserva reserva, BindingResult result, RedirectAttributes redirect) {
		Vehiculo vehiculoBuscar = this.vehiculoService.buscarPorPlaca(reserva.getVehiculo().getPlaca());
		System.out.println(reserva.getVehiculo().getPlaca());
	if(vehiculoBuscar==null||this.clienteService.buscarPorCedula(reserva.getCliente().getCedula())==null) {
		redirect.addFlashAttribute("mensaje", "Cliente o Placa no encontrada")
		.addFlashAttribute("clase", "danger");
		return "redirect:/empleados/reservar/buscarVehiculo";
	}
		BigDecimal valorTotal = this.igestorClienteService.calcularPagoVehiculo(reserva.getVehiculo().getPlaca(),
				reserva.getCliente().getCedula(), reserva.getFechaInicio(), reserva.getFechaFin());
		// LOG.info("valor total "+ valorTotal);
		Cobro cobro = new Cobro();
		cobro.setValorTotalPagar(valorTotal);
		reserva.setCobro(cobro);
		modelo.addAttribute("reserva", reserva);

		List<Reserva> reservasVehiculo = vehiculoBuscar.getReservas();
		if (reservasVehiculo == null || reservasVehiculo.isEmpty()) {
			String mensaje = "Vehiculo Disponible, Valor total a Pagar $" + valorTotal;
			redirect.addFlashAttribute("mensaje", mensaje);
			// LOG.info("Vehiculo Disponible, Valor total a Pagar "+valorTotal.toString());
			return "empleado/pagarVehiculoS";
		} else {
			for (Reserva r : reservasVehiculo) {
				if (this.igestorClienteService.verFechas(reserva.getFechaInicio(), reserva.getFechaFin(),
						r.getFechaInicio(), r.getFechaFin())) {

					redirect.addFlashAttribute("mensaje", "Fechas Solapadas, elija otras fechas");
					// LOG.info("Fechas Solapadas, elija otras fechas");
					return "empleado/reservarBuscarVehiculoS";
				}
			}
			String mensaje = "Vehiculo Disponible, Valor total a Pagar $" + valorTotal;
			redirect.addFlashAttribute("mensaje", mensaje);
			// LOG.info("Vehiculo Disponible, Valor total a Pagar "+valorTotal.toString());
			return "empleado/pagarVehiculoS";
		}

	}

	// tercer metodo para reservar vehiculo
	@PutMapping("reservar/pagarVehiculo")
	public String pagarVehiculo(Model modelo, Reserva reserva) {
		if (reserva.getCobro().getTarjeta().isEmpty()) {
			reserva.getCobro().setTarjeta(null);
		}
		Reserva reservaGenerada = this.igestorClienteService.reservarVehiculo(reserva.getVehiculo().getPlaca(),
				reserva.getCliente().getCedula(), reserva.getFechaInicio(), reserva.getFechaFin(),
				reserva.getCobro().getTarjeta());
		Reserva reservaR = this.igestorEmpleadoService.retirarVehiculoReservado(reservaGenerada.getNumero());
		modelo.addAttribute("reservaR", reservaR);
		return "empleado/retirarVehiculoMostrar";

	}

}
