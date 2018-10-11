package com.sistemasdistribuidos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistemasdistribuidos.model.Produto;
import com.sistemasdistribuidos.repository.ProdutoRepository;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	ProdutoRepository produtoRepository;
		
	@GetMapping("/listar")
	public Produto listarProdutos(){
		
		return produtoRepository.findAll().get(0);
		
	}
	
	@GetMapping("/listar/{codigo}")
	public Produto listarProdutoPorCodigo(@PathVariable String codigo) {
		
		return produtoRepository.findOne(codigo);
		
	}
	
	@PostMapping("/adicionar")
	public ResponseEntity<Produto> adicionarProduto(@RequestBody Produto produto){
		
		Produto produtoExistente = produtoRepository.findOne(produto.getCodigo());
		
		if(produtoExistente!=null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}else {
			return ResponseEntity.ok(produtoRepository.save(produto));
		}
		
	}

	@PutMapping("/alterar")
	public ResponseEntity<Produto> alterarProduto(@RequestBody Produto produto){
		
		Produto produtoExistente = produtoRepository.findOne(produto.getCodigo());

		if(produtoExistente != null ) {
			return ResponseEntity.ok(produtoRepository.save(produto));
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
	}
	
	@PostMapping("/inserir")
	public ResponseEntity<Produto> inserirProduto(@RequestBody Produto produto){
		
		if(produtoRepository.findOne(produto.getCodigo()) == null)
			return ResponseEntity.ok(produtoRepository.save(produto));
		else
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		
	}
	
	@DeleteMapping("/remover")
	public ResponseEntity<Produto> deletarProduto(@RequestBody String codigo){
		
		Produto produtoNovo = produtoRepository.findOne(codigo);
		
		if(produtoNovo != null) {
			produtoRepository.delete(produtoNovo);
			
			return ResponseEntity.ok(produtoNovo);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Produto> atualizarProduto(@RequestBody Produto produto){
		
		Produto produtoBuscado = produtoRepository.findOne(produto.getCodigo());
		
		if(produtoBuscado != null) {
			return ResponseEntity.ok(produtoRepository.save(produto));
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
}
