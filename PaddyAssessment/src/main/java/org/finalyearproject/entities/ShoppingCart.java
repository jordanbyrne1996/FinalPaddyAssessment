package org.finalyearproject.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class ShoppingCart {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cartId;
	
	@OneToMany(mappedBy = "shoppingCart")
	private Set<CartItems> cartItems;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_email", nullable = false)
	private User user;

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
	public Set<CartItems> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItems> cartItems) {
		this.cartItems = cartItems;
	}

	public double calculateTotal() {
		double total = 0;
		ArrayList<CartItems> cart_items = new ArrayList<>();
		cart_items.addAll(this.getCartItems());
		
		for(int i = 0; i < cart_items.size(); i++) {
			Item item = cart_items.get(i).getItem();
			total += item.getPrice() * cart_items.get(i).getQuantity();
		}
		
		return total;
	}
	
	public ShoppingCart(int cartId, User user) {
		this.cartId = cartId;
		this.user = user;
	}
	
	public ShoppingCart() {
		
	}

}
