package com.springproject.vmagri.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springproject.vmagri.domain.Cliente;
import com.springproject.vmagri.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;

//	@RequestMapping(method = RequestMethod.GET)
	@GetMapping
	public ResponseEntity<?> list() {

		List<Cliente> listCliente = service.find();
		return ResponseEntity.ok(listCliente);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getCliente(@PathVariable Integer id) {

		Cliente obj = service.find(id);

		return ResponseEntity.ok().body(obj);

	}

}
