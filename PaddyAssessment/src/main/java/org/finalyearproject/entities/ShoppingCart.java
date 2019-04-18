package org.finalyearproject.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class ShoppingCart {
	
	@Id
	@NotEmpty
	@Column(unique = true)
	private Long cartId;
	
	@OneToOne(mappedBy = "shoppingCart")
	private User user;
	
	

}
