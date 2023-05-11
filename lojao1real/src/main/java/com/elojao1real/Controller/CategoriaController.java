package com.elojao1real.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elojao1real.Model.Categoria;
import com.elojao1real.Repository.Categoria.CategoriaRepository;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> getById(@PathVariable Long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/categorias/{categorias}")
	public ResponseEntity<List<Categoria>> getByName(@PathVariable String categorias){
		return ResponseEntity.ok(repository.findAllByCategoriasContainingIgnoreCase(categorias));
	}

	@PostMapping
	public ResponseEntity<Categoria> postTema(@Valid @RequestBody Categoria categorias) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(categorias));
	} 
	
	@PutMapping
	public ResponseEntity<Categoria> put (@RequestBody Categoria categorias){
		return ResponseEntity.ok(repository.save(categorias));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
}