package com.santo.facturacion.app.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.santo.facturacion.app.models.entity.Cliente;
import com.santo.facturacion.app.models.entity.Factura;
import com.santo.facturacion.app.models.entity.Producto;
import com.santo.facturacion.app.services.IClienteService;

@Controller
@RequestMapping("/factura")
@SessionAttributes("factura")
public class FacturaController {
	
	@Autowired
	private IClienteService clienteService; 

	@GetMapping("/form/{clienteId}")
	public String crear(@PathVariable Long clienteId, Map<String, Object> model, RedirectAttributes flash) {
		
		Cliente cliente = clienteService.findOne(clienteId);
		if (cliente == null) {
			flash.addFlashAttribute("error", "El cliente no existe en la bdd.");
			return "redirect:clientes/listar";
		}
		
		Factura factura = new Factura();
		factura.setCliente(cliente);
		
		model.put("factura", factura);
		model.put("titulo", "Nueva Factura");
		
		return "factura/form";
	}
	
	@GetMapping(name = "/cargar-productos/{term}", produces = {"application/json"})
	public @ResponseBody List<Producto> cargarProductos(@PathVariable String term) {
		return clienteService.findByNombre(term);
	}
	
}
