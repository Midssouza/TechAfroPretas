package com.elojao1real.Controller;

import java.math.BigDecimal;
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

import com.elojao1real.Model.ProdutoModel;
import com.elojao1real.Repository.Categoria.CategoriaRepository;
import com.elojao1real.Repository.Produto;
import com.elojao1real.Repository.Produto.ProdutoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> GetAll(){	
		return ResponseEntity.ok(produtoRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> GetById(@PathVariable Long id){
		return produtoRepository.findById(id)
				.map(resp ->ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> GetByNome(@PathVariable String nomeProduto){
		return ResponseEntity.ok(produtoRepository
				.findAllByNomeProdutoContainingIgnoreCase(nomeProduto));
		
	}
	
	@GetMapping("/preco_incial/{inicio}/preco_final/{fim}")
	public ResponseEntity<List<Produto>> getByPrecoEntre(@PathVariable BigDecimal inicio, @PathVariable BigDecimal fim){
		return ResponseEntity.ok(produtoRepository.findByPrecoBetween(inicio, fim));

	}
	
	@PostMapping
	public ResponseEntity<Object> PostById(@RequestBody ProdutoModel produto){
		if (categoriaRepository.existsById(produto.getCategoria().getId()))
			return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
			return ResponseEntity.notFound().build();
		
	}
	
	
	/*@PutMapping
	public ResponseEntity<ProdutoModel> putProduto(@RequestBody ProdutoModel produto){
		return 	ResponseEntity.status(HttpStatus.OK)
				.body(produtoRepository.save(produto));
		
	}*/
	@PutMapping
	public ResponseEntity<Object> putPostagem (@Valid @RequestBody ProdutoModel produto){


		if (produtoRepository.existsById(produto.getId())){
			
			if (categoriaRepository.existsById(produto.getCategoria().getId()))
				return ResponseEntity.status(HttpStatus.OK)
						.body(produtoRepository.save(produto));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			
		}			
			
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id){
		produtoRepository.deleteById(id);
		
	}
	
	
}
