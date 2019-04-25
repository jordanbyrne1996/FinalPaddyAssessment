package org.finalyearproject.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

import org.finalyearproject.entities.CartItems;
import org.finalyearproject.entities.Item;
import org.finalyearproject.entities.ShoppingCart;
import org.finalyearproject.entities.User;
import org.finalyearproject.services.CartItemsService;
import org.finalyearproject.services.ItemService;
import org.finalyearproject.services.ShoppingCartService;
import org.finalyearproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ShoppingCartController {

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private UserService userService;

	@Autowired
	private ItemService itemService;

	@Autowired
	private CartItemsService cartItemsService;

	
	@GetMapping("/addtocart")
	public String addToCart(@RequestParam("itemId") int id, @RequestParam(defaultValue = "") String title, Model model) {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	User user = userService.findOne(auth.getName());

	ShoppingCart shoppingCart = shoppingCartService.findByUserEmail(user.getEmail());
	Item item = itemService.findOne(id);

	ArrayList<CartItems> cart_items = new ArrayList<CartItems>();
	cart_items.addAll(shoppingCart.getCartItems());
	boolean exist = true;

	for (int i = 0; i < cart_items.size(); i++) {
	CartItems current = cart_items.get(i);
	if (current.getItem() == item) {

	int temp = cart_items.get(i).getQuantity();
	cart_items.get(i).setQuantity(temp+1);

	cartItemsService.saveCartItems(cart_items.get(i));
	Set<CartItems> updatedList = new HashSet<>(cart_items);
	shoppingCart.setCartItems(updatedList);
	exist = false;
	}
	}

	if (exist) {
	CartItems cartItems = new CartItems(shoppingCart, item, 1);
	cartItemsService.saveCartItems(cartItems);
	cart_items.add(cartItems);

	Set<CartItems> updatedList = new HashSet<>(cart_items);

	shoppingCart.setCartItems(updatedList);
	}

	shoppingCartService.saveShoppingCart(shoppingCart);

	String successMessage = "";
	model.addAttribute("successMessage", successMessage);

	List<Item> items = itemService.findByTitle(title);
	model.addAttribute("items", items);

	return "views/itemList";
	}

	@GetMapping("/viewcart")
	public String viewCart(Model model) {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	User user = userService.findOne (auth.getName());
	ShoppingCart cart = shoppingCartService.findByUserEmail(user.getEmail());

	ArrayList<CartItems> cart_items = new ArrayList<CartItems>();
	cart_items.addAll(cart.getCartItems());

	double total = 0;
	for (int i = 0; i < cart_items.size(); i++) {
	CartItems cartItem = cart_items.get(i);
	Item item = itemService.findOne(cartItem.getItem().getItemId());
	total = total + (item.getPrice() * cartItem.getQuantity());
	}

	model.addAttribute("cart", cart);
	model.addAttribute("cartitems", cart_items);
	model.addAttribute("total", total);

	return "views/viewCart";
	}
}