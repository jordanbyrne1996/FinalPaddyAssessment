package org.finalyearproject.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cart_items")
@IdClass(CartItemsID.class)
public class CartItems {

	@Id
	@ManyToOne
	@JoinColumn(name = "CART_ID", referencedColumnName = "cartId")
	private ShoppingCart shoppingCart;

	@Id
	@ManyToOne
	@JoinColumn(name = "ITEM_ID", referencedColumnName = "itemId")
	private Item item;

	@Column(name = "quantity") // , columnDefinition="Decimal(4,0) default '0'")
	private int quantity;

	public CartItems() {

	}

	public CartItems(ShoppingCart shoppingCart, Item item, int quantity) {
		this.shoppingCart = shoppingCart;
		this.item = item;
		this.quantity = quantity;
	}

	public ShoppingCart getCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
