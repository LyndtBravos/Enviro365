package com.enviro.assessment.grad001.brianmthembu.enviro365.controller;

import com.enviro.assessment.grad001.brianmthembu.enviro365.model.Product;
import com.enviro.assessment.grad001.brianmthembu.enviro365.repo.InvestorRepo;
import com.enviro.assessment.grad001.brianmthembu.enviro365.repo.ProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/product")
public class ProductController
{
	private final ProductRepo productRepository;

	@GetMapping("/getAllProducts")
	public List<Product> getProducts() {
		return productRepository.findAll();
	}

	@GetMapping("/getProductsByInvestorId/{id}")
	public List<Product> getProducts(@PathVariable int id) {
		return productRepository.findByInvestorID(id);
	}

	@PostMapping("/purchaseProduct")
	public ResponseEntity addProduct(@RequestBody Product product) {
		return productRepository.insert(product);
	}

	@DeleteMapping("/deleteProduct/{id}")
	public ResponseEntity deleteProduct(@PathVariable int id) {
		return productRepository.delete(id);
	}
}
