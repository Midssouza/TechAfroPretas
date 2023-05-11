package com.elojao1real.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public class Categoria {

	@Repository
	public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
		public List<Categoria> findAllByCategoriasContainingIgnoreCase(String categorias);
	}
}