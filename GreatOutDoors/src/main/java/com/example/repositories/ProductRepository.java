package com.example.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.entities.Product;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>{


	//get product details
	
	@Query(value="select p from Product p where p.id = ?1")
	public Product findByProductId(int id);
	
	
	//get list of all products under a category
	public List<Product> findByCategory(String category);
	
	public boolean existsById(int id);
	//add product to cart
}
