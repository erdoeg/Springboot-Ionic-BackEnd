package com.springproject.vmagri.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springproject.vmagri.domain.Cliente;
import com.springproject.vmagri.repositories.ClienteRepository;
import com.springproject.vmagri.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Type: " + Cliente.class.getName()));
	}
	
	public List<Cliente> find() {
		List<Cliente> obj = repo.findAll();
		return obj;
	}

}
