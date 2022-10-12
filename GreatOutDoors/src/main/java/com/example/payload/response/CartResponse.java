package com.example.payload.response;

import com.example.entities.Cart;

public class CartResponse {
	private Cart cart;
	
	public CartResponse(Cart cart) {
		this.cart = cart;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	
}
