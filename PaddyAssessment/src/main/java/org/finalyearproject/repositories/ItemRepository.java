package org.finalyearproject.repositories;

import java.util.List;

import org.finalyearproject.entities.Item;
import org.finalyearproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
	
	List<Item> findByTitleLike(String title);

}
