package com.elojao1real.Model;


import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table (name = "tb_produtos")

public class ProdutoModel {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(min=5, max=100, message = "O atributo nome do produto deve ter no mínimo 5 e no máximo 100 caracteres! ")
	private String nomeProduto;
	
	@NotNull
	private BigDecimal preco;
	
	@NotBlank
	@Size(min=5, max=1000, message = "O atributo descrição deve ter no mínimo 5 e no máximo 1000 caracteres! ")
	private String descricao;
	
	@NotBlank
	@Size(min=5, max=100, message = "O atributo modelo deve ter no mínimo 5 e no máximo 100 caracteres! ")
	private String modelo;
	
	@Size(max = 5000, message = "O link da foto não pode ser maior do que 5000 caracteres")
	private String foto;
	
	@ManyToOne
	@JsonIgnoreProperties ("produto")
	private CategoriaModel categoria;
	
	@ManyToOne
	@JsonIgnoreProperties ("usuario")
	private UsuarioModel usuario;
	

	public CategoriaModel getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaModel categoria) {
		this.categoria = categoria;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public UsuarioModel getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioModel usuario) {
		this.usuario = usuario;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}	
	
	
	
}