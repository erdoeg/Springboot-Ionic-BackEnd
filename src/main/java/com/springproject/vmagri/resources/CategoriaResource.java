package com.springproject.vmagri.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springproject.vmagri.domain.Categoria;
import com.springproject.vmagri.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;

//	@RequestMapping(method = RequestMethod.GET)
	@GetMapping
	public List<Categoria> list() {

		Categoria cat1 = new Categoria(1, "Informatica");
		Categoria cat2 = new Categoria(2, "Escritorio");
		List<Categoria> listCategoria = new ArrayList<Categoria>();
		listCategoria.add(cat1);
		listCategoria.add(cat2);

		return listCategoria;
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getCategoria(@PathVariable Integer id) {

		Categoria obj = service.find(id);

		return ResponseEntity.ok().body(obj);

	}

}
