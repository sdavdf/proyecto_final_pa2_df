package com.uce.edu.demo.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uce.edu.demo.repository.modelo.Cliente;
import com.uce.edu.demo.repository.modelo.Cobro;
import com.uce.edu.demo.repository.modelo.Reserva;
import com.uce.edu.demo.repository.modelo.Vehiculo;
import com.uce.edu.demo.repository.modelo.VehiculoBuscar;
import com.uce.edu.demo.service.IClienteService;
import com.uce.edu.demo.service.IGestorClienteService;
import com.uce.edu.demo.service.IVehiculoService;

@Controller
@RequestMapping("/clientes/")
public class ClienteController {

	@Autowired
	private IVehiculoService vehiculoService;
	@Autowired
	private IClienteService clienteService;

	//////////////// INDEX/////////////////
	@Autowired
	private IGestorClienteService igestorClienteService;

	// clase principal
	@RequestMapping("index")
	public String mostarPaginaPricipal() {
		return "cliente/vistaIndexCliente";

	}
	////// Registrar Cliente //////////////

	// Vista para el registro del Cliente
	@GetMapping("clienteNuevo")
	public String obtenerPaginaIngresoDatos(Cliente cliente) {
		return "cliente/clienteNuevo";

	}

	// Registro el cliente y lo redirecciono a la vista de arriba
	@PostMapping("registrarCliente")

	public String registrarCliente(Cliente cliente, BindingResult resut, Model modelo,
			RedirectAttributes redirectAttributes) {
		cliente.setTipoRegistro("C");
		if (this.clienteService.buscarPorCedula(cliente.getCedula()) != null) {
			redirectAttributes.addFlashAttribute("mensaje", "Cedula ya existe").addFlashAttribute("clase", "danger");
		} else {
			this.clienteService.insertar(cliente);
			redirectAttributes.addFlashAttribute("mensaje", "Agregado correctamente").addFlashAttribute("clase",
					"success");
		}

		return "redirect:/clientes/clienteNuevo";
	}

	@GetMapping("actualizarCliente")
	public String vistaActulizar(Cliente cliente) {
		return "cliente/buscarCedula";
	}

	@GetMapping("buscar/clienteCedula")
	public String mostrarCliente(Cliente cliente, Model modelo, RedirectAttributes redirectAttributes) {
		if (this.clienteService.buscarPorCedula(cliente.getCedula()) == null) {
			redirectAttributes.addFlashAttribute("mensaje", "Cliente no registrado").addFlashAttribute("clase", "danger");
			return "redirect:/clientes/actualizarCliente";
		} else {
			Cliente clie = this.clienteService.buscarPorCedula(cliente.getCedula());
			modelo.addAttribute("clie", clie);
			return "cliente/clieActualizar";
		}

	}

	// Actualizar Cliente
	@GetMapping("/editar/{clieCedula}")
	public String editarCliente(@PathVariable("clieCedula") String cedula, Cliente cliente, Model modelo,
			RedirectAttributes redirectAttributes) {
		Cliente clie = this.clienteService.buscarPorCedula(cedula);
//        LOG.info(clie.toString());
		modelo.addAttribute("clie", clie);
		return "cliente/clieActualizar";
	}

	@PostMapping("/actualizar/{clieCedula}")
	public String actualizarCliente(@PathVariable("clieCedula") String cedula, Cliente clie,
			RedirectAttributes redirectAttrs) {
		Cliente cliente = this.clienteService.buscarPorCedula(clie.getCedula());
		clie.setId(cliente.getId());
		clie.setTipoRegistro(cliente.getTipoRegistro());
		clie.setReserva(cliente.getReserva());
		this.clienteService.actualizar(clie);
		redirectAttrs.addFlashAttribute("mensaje", "Cliente cedula: " + clie.getCedula() + " editado correctamente")
				.addFlashAttribute("clase", "success");
		return "redirect:/clientes/index";
	}

	////////////////////// 1.a ////////////////////////////////////

	// Primera vista donde el usuario debe ingresar el modelo y marca del vehículo a
	// buscar
	@GetMapping("buscarVehiculos")
	public String obtenerPaginaIngresoMarcaModelo(Vehiculo vehiculo) {
		return "cliente/buscarVehiculoDisponible";

	}

	// Segunda vista donde ya aparecen las lista de vehículos buscados por los
	// parámetros anteriores
	@GetMapping("buscar/disponibles")
	public String mostrarVehiculosDisponibles(Vehiculo vehiculo, Model modelom,RedirectAttributes redirect) {
		List<VehiculoBuscar> vehiculosBuscados = this.vehiculoService.buscarMarcaModelo(vehiculo.getMarca(),
				vehiculo.getModelo());
	
		if(vehiculosBuscados.isEmpty()) {
			redirect.addFlashAttribute("mensaje", "Vehiculo no encontrado")
			.addFlashAttribute("clase", "danger");
			return "redirect:/clientes/buscarVehiculos";
		}
		else {
			modelom.addAttribute("vehiculos", vehiculosBuscados);
			return "cliente/listaVehiculosDisponibles";
		}
		

	}

	////////////////////// 1.b ////////////////////////////////////

	@GetMapping("reservar/buscarVehiculo")
	public String obtenerPaginaBuscarVehiculo(Reserva reserva, Model modelo) {
		modelo.addAttribute("reserva", reserva);
		return "cliente/reservarBuscarVehiculo";

	}

	// segundo metodo para reservar vehiculo
	@GetMapping("verificarVehiculo")
	public String verificarVehiculo(Model modelo, Reserva reserva, BindingResult result, RedirectAttributes redirect) {
	
		Vehiculo vehiculoBuscar = this.vehiculoService.buscarPorPlaca(reserva.getVehiculo().getPlaca());
		System.out.println(reserva.getVehiculo().getPlaca());
	if(vehiculoBuscar==null||this.clienteService.buscarPorCedula(reserva.getCliente().getCedula())==null) {
		redirect.addFlashAttribute("mensaje", "Cliente o Placa no encontrada")
		.addFlashAttribute("clase", "danger");
		return "redirect:/clientes/reservar/buscarVehiculo";
	}
	
		BigDecimal valorTotal = this.igestorClienteService.calcularPagoVehiculo(reserva.getVehiculo().getPlaca(),
				reserva.getCliente().getCedula(), reserva.getFechaInicio(), reserva.getFechaFin());

		Cobro cobro = new Cobro();
		cobro.setValorTotalPagar(valorTotal);
		reserva.setCobro(cobro);
		modelo.addAttribute("reserva", reserva);

		List<Reserva> reservasVehiculo = vehiculoBuscar.getReservas();
		if (reservasVehiculo == null || reservasVehiculo.isEmpty()) {

			return "cliente/pagarVehiculo";
		} else {
			for (Reserva r : reservasVehiculo) {
				if (this.igestorClienteService.verFechas(reserva.getFechaInicio(), reserva.getFechaFin(),
						r.getFechaInicio(), r.getFechaFin())) {

					return "cliente/reservarBuscarVehiculo";

				}
			}
			return "cliente/pagarVehiculo";
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
		modelo.addAttribute("reservaGenerada", reservaGenerada);
		return "cliente/mostrarReserva";

	}

}
