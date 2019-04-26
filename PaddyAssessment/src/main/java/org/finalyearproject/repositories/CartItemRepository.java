package org.finalyearproject.repositories;

import java.util.List;

import org.finalyearproject.entities.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItems, Integer> {

	List<CartItems> findByShoppingCartCartId(int cartId);

	CartItems findByItemItemId(int itemId);
}