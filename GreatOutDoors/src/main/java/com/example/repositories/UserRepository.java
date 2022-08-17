package com.example.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.entities.User;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

	//Add user
	
	//get user info
	public User findById(int id);
	
	//public Optional<User> findByUserName(String username);
	public Optional<User> findByEmail(String email);
	
	Boolean existsByEmail(String email);
}
