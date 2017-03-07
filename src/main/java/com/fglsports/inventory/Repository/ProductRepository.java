package com.fglsports.inventory.Repository;

import org.springframework.data.repository.CrudRepository;

import com.fglsports.inventory.Model.Product;

public interface ProductRepository extends CrudRepository<Product, String> {

}
