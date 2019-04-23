package org.finalyearproject.repositories;

import org.finalyearproject.entities.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItems, Integer> {

	CartItems findByShoppingCartCartId(int cartId);

	CartItems findByItemItemId(int itemId);
}