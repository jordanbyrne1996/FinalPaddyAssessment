package org.finalyearproject.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.finalyearproject.entities.Item;
import org.finalyearproject.entities.User;
import org.finalyearproject.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ItemController {
	
	@Autowired 
	private ItemService itemService;
	
	@GetMapping("/addItem")
	public String addItemForm(Model model) {

		model.addAttribute("item", new Item());
		return "views/addItem";
	}
	
	@PostMapping("/addItem")
	public String addItem(@Valid Item item, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "views/addItem";
		}
		
		itemService.addItem(item);
		
		return "redirect:/addItem";
		//return "views/profile";
	}

	
	/*@PostMapping("/register")
    public String registerUser(@Valid User user, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "views/registerForm";
		}
		if(userService.isUserPresent(user.getEmail())) {
			model.addAttribute("exist",true);

			return "views/registerForm";

		}
		userService.createUser(user);
		
		return "views/success";

	}*/
}
