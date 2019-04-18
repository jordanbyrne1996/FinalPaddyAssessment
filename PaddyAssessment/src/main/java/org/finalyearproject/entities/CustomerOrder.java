package org.finalyearproject.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class CustomerOrder {
	
	@Id
	@NotEmpty
	@Column(unique = true)
	private Long orderId;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "ORDER_ITEMS", joinColumns={
			@JoinColumn(name = "ORDER_ID", referencedColumnName = "orderId") }, inverseJoinColumns = {
					@JoinColumn(name = "ITEM_ID", referencedColumnName = "itemId") })
	private List<Item> items;
	

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public CustomerOrder(Long orderId) {
		this.orderId = orderId;
	}
	
	public CustomerOrder() {
		
	}

}