package org.finalyearproject.services;

import java.util.List;

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
	
	public Item findOne(Long itemId) {
		
		  return itemRepository.findOne(itemId);
		}
	
	public List<Item> findByTitle(String title) {
		// TODO Auto-generated method stub
		return  itemRepository.findByTitleLike("%"+title+"%");
	}

}
