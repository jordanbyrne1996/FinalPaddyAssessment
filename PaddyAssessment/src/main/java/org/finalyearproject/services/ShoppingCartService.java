package org.finalyearproject.services;

import org.finalyearproject.entities.ShoppingCart;
import org.finalyearproject.repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cartService")
public class ShoppingCartService {

	@Autowired
	private ShoppingCartRepository shoppingCartRepository;

	
	public ShoppingCart findById(int id) {
		return shoppingCartRepository.findByCartId(id);
	}

	
	public ShoppingCart findByUserId(String userId) {
		return shoppingCartRepository.findByUserEmail(userId);
	}

	
	public void saveShoppingCart(ShoppingCart shoppingCart) {
		shoppingCartRepository.save(shoppingCart);
	}
}