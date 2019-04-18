package org.finalyearproject.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Item {
	
	@Id
	@NotEmpty
	@Column(unique = true)
	private Long itemId;
	@NotEmpty
	private String category;
	@NotEmpty
	private String manufacturer;
	@NotEmpty
	private String title;
	@NotEmpty
	private int quantity;
	@NotEmpty
	private int price;
	
	@ManyToMany(mappedBy = "items")
	private List<ShoppingCart> shoppingCarts;
	
	@ManyToMany(mappedBy = "items")
	private List<CustomerOrder> customerOrders;
	
	
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public List<ShoppingCart> getShoppingCarts() {
		return shoppingCarts;
	}
	public void setShoppingCarts(List<ShoppingCart> shoppingCarts) {
		this.shoppingCarts = shoppingCarts;
	}
	
	public List<CustomerOrder> getCustomerOrders() {
		return customerOrders;
	}
	public void setCustomerOrders(List<CustomerOrder> customerOrders) {
		this.customerOrders = customerOrders;
	}
	
	public Item(Long itemId, String category, String manufacturer, String title, int quantity, int price) {
		this.itemId = itemId;
		this.category = category;
		this.manufacturer = manufacturer;
		this.title = title;
		this.quantity = quantity;
		this.price = price;
	}
	
	public Item() {
		
	}
	

}
