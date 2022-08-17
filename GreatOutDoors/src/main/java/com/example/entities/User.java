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
@Table(name="user")
@Data
public class User implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private int id;
	
	@ToString.Exclude
	@OneToMany(mappedBy="user")
	@Cascade(CascadeType.ALL)
	private List<Order> orders;
	
	@ToString.Exclude
	@OneToMany(mappedBy="user")
	@Cascade(CascadeType.ALL)
	private List<Cart> carts;
	/*
	@OneToOne(mappedBy="user")
	@Cascade(CascadeType.ALL)
	private Cart cart;*/

	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "address_line_1")
	private String addressLine1;

	@Column(name = "address_line_2")
	private String addressLine2;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "pincode")
	private int pincode;

	public User() {
	}

	
	public User(String firstName, String lastName, String phoneNumber, String email, String password,
			String addressLine1, String addressLine2, String state, int pincode) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.state = state;
		this.pincode = pincode;
	}
	
	
	
	
}
