package org.finalyearproject.entities;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
	@NotEmpty
/*	@Column(unique = true)*/
	private int cartId;
	
	/*@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "CART_ITEMS", joinColumns={
			@JoinColumn(name = "CART_ID", referencedColumnName = "cartId") }, inverseJoinColumns = {
					@JoinColumn(name = "ITEM_ID", referencedColumnName = "itemId") })
	private List<Item> items;*/
	
	@OneToMany(mappedBy = "shoppingCart")
	private Set<CartItems> cartItems;
	
	@OneToOne(mappedBy = "shoppingCart")
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

	/*public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
*/
	public ShoppingCart(int cartId, User user) {
		this.cartId = cartId;
		this.user = user;
	}
	
	public ShoppingCart() {
		
	}

}
