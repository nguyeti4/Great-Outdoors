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
@Table(name="`order`")
@Data
public class Order implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private int orderId;
	
	@ToString.Exclude
	@ManyToOne @JoinColumn(name="user_id", referencedColumnName="id")
	private User user;
	
	/*
	@OneToOne 
	@JoinColumn(name="product_id", referencedColumnName="id")
	private Product product;*/
	@ManyToOne
	@JoinTable(name="order_product", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "id"))
	private Product product;
	
	@ManyToMany
	@JoinTable(name = "order_address", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "address_id"))
	private List<Address> addresses;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "price")
	private int price;

}
