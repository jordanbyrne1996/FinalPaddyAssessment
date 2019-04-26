package org.finalyearproject.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.finalyearproject.entities.CartItems;
import org.finalyearproject.entities.CustomerOrder;
import org.finalyearproject.entities.Item;
import org.finalyearproject.entities.MasterCard;
import org.finalyearproject.entities.ShoppingCart;
import org.finalyearproject.entities.User;
import org.finalyearproject.entities.Visa;
import org.finalyearproject.services.CartItemsService;
import org.finalyearproject.services.CustomerOrderService;
import org.finalyearproject.services.ItemService;
import org.finalyearproject.services.ShoppingCartService;
import org.finalyearproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CustomerOrderController {

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private ItemService itemService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private CustomerOrderService customerOrderService;
	
	@Autowired
	private CartItemsService cartItemsService;

	@GetMapping("/placeOrder")
	public String placeOrder(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findOne(auth.getName());
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
		model.addAttribute("cartItems", cart_items);
		model.addAttribute("total", total);

		return "views/order";

	}

	@PostMapping("/placeOrder")
	public String order(Model model, @Valid @ModelAttribute("customerOrder") CustomerOrder customerOrder, @RequestParam("total") double total, HttpServletRequest request) {
		String view = "";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findOne(auth.getName());
		Set<Item> items = new HashSet<>();
		ShoppingCart shoppingCart = user.getShoppingCart();
		ArrayList<CartItems> cart_items = new ArrayList<CartItems>();
		cart_items.addAll(shoppingCart.getCartItems());

		/*purchaseFacade = new PurchaseFacadeImp();

		if (purchaseFacade.placeOrder(cart_items)) {*/

			items.addAll(items);

			CustomerOrder order = new CustomerOrder(user, items, total);

			if (request.getParameter("payment_method").equals("Visa")) {

				Visa visa = new Visa(request.getParameter("name"), request.getParameter("cardNumber"), request.getParameter("expires"));

				if (order.pay(visa, shoppingCart)) {
					order.setPaymentMethod("Visa");
					order.setAddress(user.getShippingAddress());
					customerOrderService.saveOrder(order);
					itemService.updateStock(cart_items);
					cartItemsService.emptyCart(cartItemsService.findByCartId(shoppingCart.getCartId()));

					view = "views/visaSuccess";
				} else {
					String visaError = "";
					model.addAttribute("visaError", visaError);
					model.addAttribute("total", total);
					view = "views/order";
				}
			} else if (request.getParameter("payment_method").equals("Mastercard")) {

				MasterCard mastercard = new MasterCard(request.getParameter("name"), request.getParameter("cardNumber"), request.getParameter("expires"));

				if (order.pay(mastercard, shoppingCart)) {
					order.setPaymentMethod("MasterCard");
					customerOrderService.saveOrder(order);
					itemService.updateStock(cart_items);
					cartItemsService.emptyCart(cartItemsService.findByCartId(shoppingCart.getCartId()));

					view = "views/masterSuccess";
				} else {
					String mastercardError = "";
					model.addAttribute("total", total);
					model.addAttribute("mastercardError", mastercardError);
					view = "views/order";
				}
			}
			return view;

		/*} else {
			model.addAttribute("total", total);
			return "views/order";
		}*/
	}

}
