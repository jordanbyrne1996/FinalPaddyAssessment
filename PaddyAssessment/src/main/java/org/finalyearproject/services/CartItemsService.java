package org.finalyearproject.services;

import org.finalyearproject.entities.CartItems;
import org.finalyearproject.repositories.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cartItemsService")
public class CartItemsService {

	@Autowired
	private CartItemRepository cartItemRepository;

	
	public CartItems findByCartId(int cartId) {
		return cartItemRepository.findByShoppingCartCartId(cartId);
	}

	
	public CartItems findByItemId(int itemId) {
		return cartItemRepository.findByItemItemId(itemId);
	}

	
	public void saveCartItems(CartItems cartItems) {
		cartItemRepository.save(cartItems);
	}
}
