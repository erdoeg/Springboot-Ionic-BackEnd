package com.springproject.vmagri.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.springproject.vmagri.domain.Cliente;
import com.springproject.vmagri.dto.ClienteDTO;
import com.springproject.vmagri.repositories.ClienteRepository;
import com.springproject.vmagri.services.exceptions.DataIntegrityException;
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

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
			
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("O Cliente nao pode ser excluído. Poder haver pedidos relacionados.", e);
		}
	}
	
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),
				orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getName(), objDTO.getEmail(), null, null);
	}
	
	private void updateData (Cliente newObj, Cliente obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
}
