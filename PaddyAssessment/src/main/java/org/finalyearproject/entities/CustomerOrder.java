package org.finalyearproject.entities;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class CustomerOrder {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long orderId;

	@ManyToOne
	@JoinColumn(name = "USER_EMAIL")
	private User user;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "ORDER_ITEMS", joinColumns = {
			@JoinColumn(name = "ORDER_ID", referencedColumnName = "orderId") }, inverseJoinColumns = {
					@JoinColumn(name = "ITEM_ID", referencedColumnName = "itemId") })
	private Set<Item> items;

	private double total;
	private String paymentMethod;
	private String address;

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public CustomerOrder(User user, Set<Item> items, double total) {
		this.user = user;
		this.items = items;
		this.total = total;
	}

	public CustomerOrder() {

	}

	public boolean pay(PaymentMethod method, ShoppingCart shoppingCart) {
		double totalCost = shoppingCart.calculateTotal();
		return method.pay(totalCost);
	}

}
