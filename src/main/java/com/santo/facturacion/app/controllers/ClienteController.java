package com.santo.facturacion.app.controllers;

import java.util.Map;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.santo.facturacion.app.models.entity.Cliente;
import com.santo.facturacion.app.services.IClienteService;
import com.santo.facturacion.app.util.paginator.PageRender;

@Controller
@RequestMapping("/clientes")
@SessionAttributes("cliente")
public class ClienteController {

	Logger log = LoggerFactory.getLogger(ClienteController.class);
	
	@Autowired
	private IClienteService clienteService;

	@GetMapping("/listar")
	public String listar(@RequestParam(name="page", defaultValue = "0") int page, Model model) {
		
		Pageable pageRequest = PageRequest.of(page, 4);
		Page<Cliente> clientes = clienteService.findAll(pageRequest);
		PageRender<Cliente> pageRender = new PageRender<>("/clientes/listar", clientes);
		log.info("Pagina primera? " + pageRender.isFirst());
		model.addAttribute("titulo", "Listado de Clientes");
		model.addAttribute("clientes", clientes);
		model.addAttribute("page", pageRender);
		return "listar";
	}

	@GetMapping("/form")
	public String crear(Model model) {
		Cliente cliente = new Cliente();
		model.addAttribute("cliente", cliente);
		model.addAttribute("titulo", "Formulario Cliente");
		return "form";
	}

	@GetMapping("/form/{id}")
	public String editar(@PathVariable Long id, Map<String, Object> model, RedirectAttributes flash) {
		Cliente cliente = null;
		if (id > 0) {
			try {
				cliente = clienteService.findOne(id);
			} catch (NoSuchElementException e) {
				flash.addFlashAttribute("error", "Cliente a editar no existe");
				return "redirect:/clientes/listar";
			}
		} else {
			flash.addFlashAttribute("error", "Cliente a editar no existe");
			return "redirect:/clientes/listar";
		}
		model.put("cliente", cliente);
		model.put("titulo", "Formulario Cliente");
		return "form";
	}
	
	@GetMapping("/ver/{id}")
	public String ver(@PathVariable Long id, Model model) {
		Cliente cliente = clienteService.findOne(id);
		model.addAttribute("cliente", cliente);
		model.addAttribute("titulo", "Ficha del Cliente");
		return "ver";
	}

	@PostMapping("/form")
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario Cliente");
			return "form";
		}
		clienteService.guardar(cliente);
		status.setComplete();
		flash.addFlashAttribute("success", "Cliente guardado correctamente");
		return "redirect:/clientes/listar";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Long id, RedirectAttributes flash) {
		if (id > 0) {
			clienteService.delete(id);
			flash.addFlashAttribute("success", "Se ha eliminado con éxito el cliente");
		}

		return "redirect:/clientes/listar";
	}
}
