package com.santo.facturacion.app.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class ItemFactura implements Serializable {

	private static final long serialVersionUID = -4372184437000509111L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "factura_generator")
	@SequenceGenerator(name = "factura_generator", sequenceName = "factura_seq", allocationSize = 10)
	private Long id;

	private Integer cantidad;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "producto_id")
	private Producto producto;
	
	public Double calcularTotal() {
		return cantidad * producto.getPrecio();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	
}
