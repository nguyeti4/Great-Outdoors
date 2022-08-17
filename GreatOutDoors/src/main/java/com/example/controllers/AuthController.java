package com.example.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Cart;
import com.example.entities.Query;
import com.example.entities.User;
import com.example.payload.response.JwtResponse;
import com.example.payload.response.MessageResponse;
import com.example.payload.request.EmailDetails;
import com.example.payload.request.LoginRequest;
import com.example.payload.request.SignupRequest;
import com.example.repositories.CartRepository;
import com.example.repositories.QueryRepository;
import com.example.repositories.UserRepository;
import com.example.security.jwt.JwtUtils;
import com.example.security.services.EmailService;
import com.example.security.services.UserDetailsImpl;

//@CrossOrigin(origins = "*", maxAge = 3600)
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	QueryRepository queryRepository;
	
	private static final Logger logger=LogManager.getLogger(AuthController.class);

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		//If we want to get more data (id, email…), 
		//we can create an implementation UserDetailsImpl.java of this UserDetails interface.

		//authenticate { username, pasword }
		//If the authentication process is successful, 
		//we can get User’s information such as username, password, authorities from an Authentication object.
				
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		//update SecurityContext using Authentication object
		//set the current UserDetails in SecurityContext using setAuthentication(authentication) method.
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		//generate JWT
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		//get UserDetails from Authentication object
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		

		//response contains JWT and UserDetails data
		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getEmail(),
												 userDetails.getFirstName(),
												 userDetails.getLastName(),
												 userDetails.getPhoneNumber(),
												 userDetails.getAddressLine1(),
												 userDetails.getAddressLine2(),
												 userDetails.getState(),
												 userDetails.getPincode()
												 ));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		//check existing username/email
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			logger.error("Error: You can't signup with this email because it's already in use");
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User( signUpRequest.getFirstName(), signUpRequest.getLastName(), signUpRequest.getPhone(), signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()),
							  signUpRequest.getAddr1(),signUpRequest.getAddr2(), signUpRequest.getState(), signUpRequest.getPin());
		
		userRepository.save(user);
	/*	
		Cart cart = new Cart();
		
		cart.setUser(user);
		
		cartRepository.save(cart);*/
		
		logger.info("User was registered successfully");
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	@PostMapping("/signout")
	  public ResponseEntity<?> logoutUser() {
		logger.info("User logged out successfully");
	   // ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
	    return ResponseEntity.ok(new MessageResponse("You've been signed out!"));
	  }
	
	
	@PostMapping("/sendMail")
	public String sendMail(@RequestBody EmailDetails details) {
		Query q = new Query();
		q.setFirstName(details.getFirstName());
		q.setLastName(details.getLastName());
		q.setEmail(details.getEmail());
		q.setQuery(details.getQuery());
		queryRepository.save(q);
		String status = emailService.sendSimpleMail(details);
		return status;
	}
	
	
	
}
