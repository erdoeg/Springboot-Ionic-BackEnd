package com.springproject.vmagri.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springproject.vmagri.domain.Cliente;
import com.springproject.vmagri.dto.ClienteDTO;
import com.springproject.vmagri.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;

//	@RequestMapping(method = RequestMethod.GET)
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> list() {
		List<Cliente> listCliente = service.find();
		List<ClienteDTO> listDto = listCliente.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok(listDto);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getCliente(@PathVariable Integer id) {

		Cliente obj = service.find(id);

		return ResponseEntity.ok().body(obj);
	}
	
//	@PostMapping
//	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteDTO objDTO){
//		Cliente obj = service.fromDTO(objDTO);
//		obj = service.insert(obj);
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
//			.path("/{id}").buildAndExpand(obj.getId()).toUri();
//		return ResponseEntity.created(uri).build();
//	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDTO, @PathVariable Integer id){
		Cliente obj = service.fromDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/page")
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value="page", defaultValue = "0") Integer page,
			@RequestParam(value="lines", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue = "name") String orderBy,
			@RequestParam(value="direction", defaultValue = "ASC") String direction) {
		Page<Cliente> listCliente = service.findPage(page, linesPerPage, orderBy, direction);
		
		Page<ClienteDTO> listDto = listCliente.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok(listDto);
	}

}
