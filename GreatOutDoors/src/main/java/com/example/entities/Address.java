package com.example.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;


import lombok.Data;
import lombok.ToString;

@Entity
@Table(name="address")
@Data
public class Address implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private int addressId;
	
	@ToString.Exclude
	@ManyToMany(mappedBy="addresses")
	private List<Order> orders;
	
	@Column(name = "address_line_1")
	private String addressLine1;
	
	@Column(name = "address_line_2")
	private String addressLine2;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "pincode")
	private int pincode;
}
