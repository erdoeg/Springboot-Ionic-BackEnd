package com.springproject.vmagri.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springproject.vmagri.domain.Pedido;
import com.springproject.vmagri.repositories.PedidoRepository;
import com.springproject.vmagri.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Type: " + Pedido.class.getName()));
	}
	
	public List<Pedido> find() {
		List<Pedido> obj = repo.findAll();
		return obj;
	}

}
