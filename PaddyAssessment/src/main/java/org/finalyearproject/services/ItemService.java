package org.finalyearproject.services;

import java.util.ArrayList;
import java.util.List;

import org.finalyearproject.entities.CartItems;
import org.finalyearproject.entities.Item;
import org.finalyearproject.entities.User;
import org.finalyearproject.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;

	public void addItem(Item item) {
		itemRepository.save(item);
	}

	public Item findOne(int id) {

		return itemRepository.findOne(id);
	}

	public List<Item> findByTitle(String title) {
		// TODO Auto-generated method stub
		return itemRepository.findByTitleLike("%" + title + "%");
	}

	public void updateStock(ArrayList<CartItems> cartItems) {
		for (int i = 0; i < cartItems.size(); i++) {
			CartItems cartItem = cartItems.get(i);
			Item item = cartItem.getItem();

			int stockLevel = item.getStock() - cartItem.getQuantity();
			item.setStock(stockLevel);
			this.addItem(item);
		}
	}

}
