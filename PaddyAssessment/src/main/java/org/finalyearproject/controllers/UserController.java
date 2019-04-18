package org.finalyearproject.controllers;

import org.finalyearproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
	  
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public String listUsers(Model model, @RequestParam(defaultValue="")  String name) {
		model.addAttribute("users", userService.findByName(name));
		return "views/list";
	}
	
	@GetMapping("/sample")
	public String sampleEvent() {
		
		return "views/sample";
	}

}
