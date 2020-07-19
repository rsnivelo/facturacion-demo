package com.santo.facturacion.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.santo.facturacion.app.models.dao.IClienteDao;
import com.santo.facturacion.app.models.dao.IProductoDao;
import com.santo.facturacion.app.models.entity.Cliente;
import com.santo.facturacion.app.models.entity.Producto;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteDao clienteDao;
	
	@Autowired
	private IProductoDao productoDao;
	
	@Override
	public List<Cliente> findAll() {
		return clienteDao.findAll();
	}

	@Override
	public void guardar(Cliente c) {
		clienteDao.save(c);

	}

	@Override
	public Cliente findOne(Long id) {
		return clienteDao.findById(id).get();
	}

	@Override
	public void delete(Long id) {
		clienteDao.deleteById(id);
	}

	@Override
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteDao.findAll(pageable);
	}

	@Override
	public List<Producto> findByNombre(String term) {
		return productoDao.findByNombre(term);
	}

}
