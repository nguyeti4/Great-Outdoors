package com.example.controllers;


import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Address;
import com.example.entities.Cart;
import com.example.entities.Order;
import com.example.entities.Product;
import com.example.entities.User;
import com.example.payload.request.PlaceOrderRequest;
import com.example.payload.request.EmailDetails;
import com.example.payload.request.LoginRequest;
import com.example.payload.request.SignupRequest;
import com.example.payload.response.CartResponse;
import com.example.payload.response.MessageResponse;
import com.example.repositories.AddressRepository;
import com.example.repositories.CartRepository;
import com.example.repositories.OrderRepository;
import com.example.repositories.ProductRepository;
import com.example.repositories.UserRepository;
import com.example.security.jwt.JwtUtils;
import com.example.security.services.EmailService;



//@CrossOrigin(origins = "*", maxAge = 3600)
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/test")
public class TestController {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CartRepository cartRepository;

	@Autowired
	AddressRepository addrRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	JwtUtils jwtUtils;
	
	private static final Logger logger=LogManager.getLogger(TestController.class);

	
	/*
	@DeleteMapping("/removeProduct/{productId}/{cartId}")
	public ResponseEntity<?> removeProduct(@PathVariable int productId, @PathVariable int cartId ) {
		
		Product p = productRepository.getById(productId);

		Cart cart = cartRepository.getById(cartId);
		List<Product> products = cart.getProducts();
		products.remove(p);
		cart.setProducts(products);
		
		cartRepository.save(cart);
		
		logger.info("You successfully removed the product from the cart");
		return ResponseEntity.ok(new MessageResponse("Successfully removed product from cart!"));
	}
	
	
	@PostMapping("/addProduct/{productId}/{cartId}")
	public ResponseEntity<?> registerUser(@PathVariable int productId, @PathVariable int cartId ) {
		
		
		Product p = productRepository.getById(productId);

		Cart cart = cartRepository.getById(cartId);
		
		if(cart.getProducts().contains(p)) {
			logger.error("Error: You can't add this product to the cart because its already there");
			return ResponseEntity
					.ok(new MessageResponse("Product is already in your cart!"));
		}
		
		List<Product> products = cart.getProducts();
		products.add(p);
		cart.setProducts(products);
		
		cartRepository.save(cart);
		
		logger.info("You successfully added the product to the cart");
		return ResponseEntity.ok(new MessageResponse("Successfully added product!"));
	}*/
	
	//@PreAuthorize("hasRole('USER')")
	@PostMapping("/addOrder/{productId}/{quantity}/{userId}")
	public ResponseEntity<?> addOrder(@PathVariable int productId, @PathVariable int quantity, @PathVariable int userId,@Valid @RequestBody PlaceOrderRequest orderRequest){
		User user = userRepository.getById(userId);
		Product p = productRepository.getById(productId);
		Order order = new Order();
		List<Address> addresses = orderRequest.getAddresses();
		addresses.forEach(addr -> addrRepository.save(addr));
		order.setUser(user);
		order.setProduct(p);
		order.setAddresses(addresses);
		order.setQuantity(quantity);
		order.setPrice(orderRequest.getPrice());
		orderRepository.save(order);
		logger.info("You successfully placed an order");
		return ResponseEntity.ok(new MessageResponse("Successfully placed Order!"));

	}
	
	
	@PostMapping("/addProduct/{productId}/{userId}/{quantity}")
	public ResponseEntity<?> addProduct(@PathVariable int productId, @PathVariable int userId, @PathVariable int quantity ) {
		//check if product already exists
		
		Product p = productRepository.getById(productId);
		User u = userRepository.getById(userId);
		
		Cart cart = new Cart();
		
		List<Cart> cartItems = cartRepository.findByUserId(userId);
		for(Cart cartItem: cartItems) {
			if(cartItem.getProduct().equals(p)) {
				logger.error("Error: You can't add this product to the cart because its already there");
				return ResponseEntity
						.ok(new MessageResponse("Product is already in your cart!"));
			}
		}
		
		cart.setUser(u);
		cart.setProduct(p);
		cart.setQuantity(quantity);
		//save Cart to database using CartRepository
		cartRepository.save(cart);
		
		logger.info("You successfully added the product to the cart");
		return ResponseEntity.ok(new MessageResponse("Successfully added product!"));
	}
	
	
	@DeleteMapping("/removeProduct/{userId}/{cartId}")
	public ResponseEntity<?> removeProduct(@PathVariable int userId, @PathVariable int cartId ) {
		System.out.println("UserId: "+userId);
		User us = userRepository.findById(userId);
		List<Cart> carts = us.getCarts();
		
		System.out.println("CartId: "+cartId);
		Cart c = cartRepository.findByCartId(cartId);
		System.out.println("CartItem: "+c);
		c.setUser(null);
		
		carts.remove(c);
		us.setCarts(carts);
		userRepository.save(us);
		cartRepository.delete(c);
	
		logger.info("You successfully removed the product from the cart");
		return ResponseEntity.ok(new MessageResponse("Successfully removed product from cart!"));
	}
	
	
	@PostMapping("/changeQuantity/{cartId}/{quantity}")
	public ResponseEntity<?> changeQuantity(@PathVariable int cartId, @PathVariable int quantity){
		System.out.println("Cart: "+cartId);
		Cart c = cartRepository.findByCartId(cartId);
		System.out.println("Quantity: "+quantity);
		c.setQuantity(quantity);
		cartRepository.save(c);
		logger.info("Successfully changed quantity of cartItem!");
		return ResponseEntity.ok(new MessageResponse("Successfully changed quantity of cartItem!"));
	}
	/*
	@GetMapping("/getCart/{userId}/{productId}")
	public Cart getCart(@PathVariable int userId,@PathVariable int productId){
		Product p = productRepository.findByProductId(productId);
		System.out.println("P: "+p);
		List<Cart> cartItems = cartRepository.findByUserId(userId);
		for(Cart cartItem: cartItems) {
			if(cartItem.getProduct().equals(p)) {
				System.out.println("CartItem: "+cartItem);
				return cartItem;
			}
		}
		return null;
	}*/
	
	/*
	@GetMapping("/getCartItems/{userId}")
	public ResponseEntity<?> getCartItems(@PathVariable int userId){
		User user = userRepository.getById(userId);
		List<Cart> cartItems = cartRepository.findByUserId(userId);
		System.out.println("CartItems: "+cartItems);
		return ResponseEntity.ok(cartItems);
	}*/
	
	
}
