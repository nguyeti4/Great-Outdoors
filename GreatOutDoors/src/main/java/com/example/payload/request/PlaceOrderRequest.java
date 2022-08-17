package com.example.payload.request;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.example.entities.Address;

public class PlaceOrderRequest {
	@NotBlank
	private List<Address> addresses;

	@NotBlank
	private int price;
	
	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
	
	
}
