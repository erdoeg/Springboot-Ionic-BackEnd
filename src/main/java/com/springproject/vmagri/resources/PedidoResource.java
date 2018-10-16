package com.springproject.vmagri.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springproject.vmagri.domain.Pedido;
import com.springproject.vmagri.services.PedidoService;

@RestController
@RequestMapping(value = "/Pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService service;

//	@RequestMapping(method = RequestMethod.GET)
	@GetMapping
	public ResponseEntity<?> list() {

		List<Pedido> listPedido = service.find();
		return ResponseEntity.ok(listPedido);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getPedido(@PathVariable Integer id) {

		Pedido obj = service.find(id);

		return ResponseEntity.ok().body(obj);

	}

}
