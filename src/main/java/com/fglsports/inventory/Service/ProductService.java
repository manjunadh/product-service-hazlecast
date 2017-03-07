package com.fglsports.inventory.Service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.fglsports.inventory.Model.Product;
import com.fglsports.inventory.Repository.ProductRepository;

@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<>();
		productRepository.findAll().forEach(products::add);
		return products;
	}
	@Cacheable(value = "products", key = "#id")
	public Product getProduct(String id) {
		return productRepository.findOne(id);
	}
	@CachePut(value = "products", key = "#result.id")
	public void addProduct(Product product) {
		productRepository.save(product);
	}
	@CachePut(value = "products", key = "#product.id")
	public void updateProduct(String id, Product product) {
		productRepository.save(product);
	}
	@CacheEvict(value = "products", key = "#id")
	public void deleteProduct(String id) {
		productRepository.delete(id);
	}
	@CacheEvict(value = "products", allEntries = true)
	public void evict() {
	}

}
