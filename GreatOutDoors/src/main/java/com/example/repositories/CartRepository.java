package com.example.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.entities.Cart;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
	
	//get all info on cart
	
	public Cart findByCartId(int id);
	
	public List<Cart> findByUserId(int id);
	
	
}
