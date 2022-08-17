package com.example.entities;

import java.io.Serializable;

import javax.persistence.*;


import lombok.Data;

@Entity
@Table(name="query")
@Data
public class Query implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private int id;
	
	
	@Column(name = "first_name")
	private String firstName;
	
	
	@Column(name = "last_name")
	private String lastName;
	
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "query")
	private String query;
}
