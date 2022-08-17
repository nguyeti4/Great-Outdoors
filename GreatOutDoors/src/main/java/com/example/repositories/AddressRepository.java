package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.entities.Address;
import com.example.entities.Cart;

//@CrossOrigin("http://localhost:4200")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@Repository
public interface AddressRepository extends JpaRepository<Address,Integer>  {

	//get all info on Address
	public Address findByAddressId(int id);
}
