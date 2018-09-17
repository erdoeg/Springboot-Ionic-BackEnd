package com.springproject.vmagri.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springproject.vmagri.domain.Categoria;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
//	@RequestMapping(method = RequestMethod.GET)
	@GetMapping
	public List<Categoria> list() {
		
		Categoria cat1 = new Categoria (1, "Informatica");
		Categoria cat2 = new Categoria (2, "Escritorio");
		List<Categoria> listCategoria = new ArrayList<Categoria>();
		listCategoria.add(cat1);
		listCategoria.add(cat2);
		
		return listCategoria;
	}

}
