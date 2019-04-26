package org.finalyearproject.services;

import java.util.List;

import org.finalyearproject.entities.CartItems;
import org.finalyearproject.repositories.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cartItemsService")
public class CartItemsService {

	@Autowired
	private CartItemRepository cartItemRepository;

	
	public List<CartItems> findByCartId(int cartId) {
		return cartItemRepository.findByShoppingCartCartId(cartId);
	}

	
	public CartItems findByItemId(int itemId) {
		return cartItemRepository.findByItemItemId(itemId);
	}

	
	public void saveCartItems(CartItems cartItems) {
		cartItemRepository.save(cartItems);
	}
	
	public void emptyCart(List<CartItems> list) {
		cartItemRepository.delete(list);
	}
}
