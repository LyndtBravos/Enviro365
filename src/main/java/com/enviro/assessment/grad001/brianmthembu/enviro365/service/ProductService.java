package com.enviro.assessment.grad001.brianmthembu.enviro365.service;

import com.enviro.assessment.grad001.brianmthembu.enviro365.model.Product;
import com.enviro.assessment.grad001.brianmthembu.enviro365.repo.ProductRepo;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService
{
	@Autowired
	private ProductRepo repo;

	public List<Product> getProducts() {
		return repo.findAll();
	}

	public List<Product> getProductsByInvestorId(int id) {
		return repo.findByInvestorID(id);
	}

	public ResponseEntity<String> addProduct(Product product) {
		return repo.insert(product);
	}

	public ResponseEntity<String> deleteProduct(int id) {
		return repo.delete(id);
	}
}
