package org.finalyearproject.repositories;

import org.finalyearproject.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {

	ShoppingCart findByCartId(int cartId);

	ShoppingCart findByUserEmail(String email);
}
