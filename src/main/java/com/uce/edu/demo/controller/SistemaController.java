package com.uce.edu.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sistema/")
public class SistemaController {
	@RequestMapping("index")
	public String paginaPrincipal() {
		return "principal/index";
	}
	@RequestMapping("/cliente/")
	public String paginaCliente() {
		return "";
	}
	@RequestMapping("/empleado/")
	public String paginaEmpleado() {
		return "";
	}
}
