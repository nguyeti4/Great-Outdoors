package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.entities.Order;

//@CrossOrigin("http://localhost:4200")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {

	//show order summary
	public Order findByOrderId(int id);
}
