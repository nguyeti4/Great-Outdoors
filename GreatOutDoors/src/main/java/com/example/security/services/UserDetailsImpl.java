package com.example.security.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.Column;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.entities.Cart;
import com.example.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private int id;

	private String email;

	@JsonIgnore
	private String password;
	
	private String firstName;

	private String lastName;

	private String phoneNumber;
	
	private String addressLine1;

	private String addressLine2;
	
	private String state;
	
	private int pincode;
	

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(int id, String email, String password, String firstName, String lastName, String phoneNumber, String addr1, String addr2, String state, int pin, Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.addressLine1 = addr1;
		this.addressLine2 = addr2;
		this.state = state;
		this.pincode = pin;
		this.authorities = authorities;
	}

	public static UserDetailsImpl build(User user) {
		List<String> roles = new ArrayList<String>();
		roles.add("USER");
		List<GrantedAuthority> authorities = roles.stream()
				.map(role -> new SimpleGrantedAuthority(role))
				.collect(Collectors.toList());
		return new UserDetailsImpl(user.getId(),user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getPhoneNumber(), user.getAddressLine1(), user.getAddressLine2(), user.getState(), user.getPincode(),authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public String getState() {
		return state;
	}

	public int getPincode() {
		return pincode;
	}
	


	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}
}
