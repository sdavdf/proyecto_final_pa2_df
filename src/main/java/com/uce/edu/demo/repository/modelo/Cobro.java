package com.uce.edu.demo.repository.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "cobro")
public class Cobro {

	@Id
	@Column(name = "cobr_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cobro")
	@SequenceGenerator(name = "seq_cobro", sequenceName = "seq_cobro", allocationSize = 1)
	private Integer id;

	@Column(name = "cobr_fecha")
	private LocalDateTime fecha;

	@Column(name = "cobr_valor_subtotal")
	private BigDecimal valorSubtotal;

	@Column(name = "cobr_valor_iva")
	private BigDecimal valorIVA;

	@Column(name = "cobr_valor_total_pagar")
	private BigDecimal valorTotalPagar;

	@Column(name = "cobr_tarjeta")
	private String tarjeta;

	@OneToOne(mappedBy = "cobro")
	private Reserva reserva;

	// SET Y GET
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getValorSubtotal() {
		return valorSubtotal;
	}

	public void setValorSubtotal(BigDecimal valorSubtotal) {
		this.valorSubtotal = valorSubtotal;
	}

	public BigDecimal getValorIVA() {
		return valorIVA;
	}

	public void setValorIVA(BigDecimal valorIVA) {
		this.valorIVA = valorIVA;
	}

	public BigDecimal getValorTotalPagar() {
		return valorTotalPagar;
	}

	public void setValorTotalPagar(BigDecimal valorTotalPagar) {
		this.valorTotalPagar = valorTotalPagar;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public String getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}

}
