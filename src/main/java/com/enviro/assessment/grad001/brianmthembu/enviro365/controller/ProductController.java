package com.enviro.assessment.grad001.brianmthembu.enviro365.controller;

import com.enviro.assessment.grad001.brianmthembu.enviro365.model.Product;
import com.enviro.assessment.grad001.brianmthembu.enviro365.repo.InvestorRepo;
import com.enviro.assessment.grad001.brianmthembu.enviro365.repo.ProductRepo;
import com.enviro.assessment.grad001.brianmthembu.enviro365.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/product")
public class ProductController
{
	private final ProductService service;

	public ProductController(ProductService service) {
		this.service = service;
	}

	@GetMapping("/getAllProducts")
	public ResponseEntity<List<Product>> getProducts() {
		List<Product> products = service.getProducts();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@GetMapping("/getProductsByInvestorId/{id}")
	public ResponseEntity<List<Product>> getProducts(@PathVariable int id) {
		List<Product> products = service.getProductsByInvestorId(id);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@PostMapping("/purchaseProduct")
	public ResponseEntity<String> addProduct(@RequestBody Product product) {
		return service.addProduct(product);
	}

	@DeleteMapping("/deleteProduct/{id}")
	public ResponseEntity deleteProduct(@PathVariable int id) {
		return service.deleteProduct(id);
	}
}
