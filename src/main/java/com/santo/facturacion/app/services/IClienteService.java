package com.santo.facturacion.app.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.santo.facturacion.app.models.entity.Cliente;
import com.santo.facturacion.app.models.entity.Producto;

public interface IClienteService {

	public List<Cliente> findAll();
	
	public Page<Cliente> findAll(Pageable pageable);

	public void guardar(Cliente c);

	public Cliente findOne(Long id);

	void delete(Long id);
	
	public List<Producto> findByNombre(String term);
}
