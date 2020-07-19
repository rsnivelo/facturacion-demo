package com.santo.facturacion.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santo.facturacion.app.models.entity.Producto;


public interface IProductoDao extends JpaRepository<Producto, Long> {
	
	public List<Producto> findByNombre(String term);

}
