package com.santo.facturacion.app.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santo.facturacion.app.models.entity.Cliente;


public interface IClienteDao extends JpaRepository<Cliente, Long> {

}
