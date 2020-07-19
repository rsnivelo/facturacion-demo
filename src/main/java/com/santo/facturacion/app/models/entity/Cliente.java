package com.santo.facturacion.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table
public class Cliente implements Serializable {

	private static final long serialVersionUID = -4372184437000509111L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_generator")
	@SequenceGenerator(name = "cliente_generator", sequenceName = "cliente_seq", allocationSize = 10)
	private Long id;
	
	@NotEmpty
	@Column(nullable = false)
	private String nombres;

	@NotEmpty
	@Column(nullable = false)
	private String apellidos;

	@Column(nullable = false)
	@NotEmpty
	@Email
	private String email;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@NotNull
	private Date fechaCreacion;
	
	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Factura> facturasList;
	
	public Cliente() {
		facturasList = new ArrayList<Factura>();
	}
	
	@PrePersist
	public void prePersist() {
		fechaCreacion = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public List<Factura> getFacturasList() {
		return facturasList;
	}

	public void setFacturasList(List<Factura> facturasList) {
		this.facturasList = facturasList;
	}
	
	public void addFactura(Factura factura) {
		facturasList.add(factura);
	}

}
