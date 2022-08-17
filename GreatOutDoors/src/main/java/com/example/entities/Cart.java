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

@Entity
@Table(name="cart")
@Data
public class Cart implements Serializable{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cart_id")
	private int cartId;
	
	
	@ManyToOne
	@JoinTable(name = "cart_user",joinColumns = @JoinColumn(name="cartitem_id", referencedColumnName="cart_id"), inverseJoinColumns = @JoinColumn(name="user_id", referencedColumnName="id"))
	private User user;
	/*
	@OneToOne @JoinColumn(name="user_id", referencedColumnName="id")
	private User user;*/
	
	/*2
	@OneToOne
	@JoinColumn(name="product_id", referencedColumnName="id")
	private Product product;*/
	/*
	@ManyToMany
	@JoinTable(name = "cart_product",joinColumns = @JoinColumn(name="cart_id"),inverseJoinColumns = @JoinColumn(name="product_id", referencedColumnName="id"))
	private List<Product> products;*/
	
	@ManyToOne
	@JoinTable(name = "cart_product",joinColumns = @JoinColumn(name="cart_id"),inverseJoinColumns = @JoinColumn(name="product_id", referencedColumnName="id"))
	private Product product;;
	
	private int quantity;

}
