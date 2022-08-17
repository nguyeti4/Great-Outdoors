package com.example.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;


import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name="product")
@Data
public class Product implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private int id;
	
	/*
	@OneToOne(mappedBy="product")
	@Cascade(CascadeType.ALL)
	private Order order;*/
	
	@ToString.Exclude
	@OneToMany(mappedBy="product")
	@Cascade(CascadeType.ALL)
	private List<Order> orders;
	
	@ToString.Exclude
	@OneToMany(mappedBy = "product")
	@Cascade(CascadeType.ALL)
	private List<Cart> carts;
	
	/*2
	@OneToOne(mappedBy = "product")
	@Cascade(CascadeType.ALL)
	private Cart cart;*/
/*	
	@ManyToMany(mappedBy = "products")
	private List<Cart> carts;*/

	
	@Column(name = "name")
	private String name;
	

	@Column(name = "description")
	private String description;
	
	
	@Column(name = "price")
	private int price;
	
	
	@Column(name = "category")
	private String category;
	
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "image_url")
	private String imageUrl;
	
}
