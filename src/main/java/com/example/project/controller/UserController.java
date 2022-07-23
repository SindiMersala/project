package com.example.project.controller;

import com.example.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {
	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}


	@GetMapping("/")
	public String showHome(Model model, Principal principal) {
		var usr = userService.getUserByPrincipal(principal);
		if (usr.isPresent()) {
			model.addAttribute("user", usr.get());
			return "user/account";
		}
		log.error("No user with username {} can be found", principal.getName());
		return "error/404";
	}


	@GetMapping("/~")
	public String userHome(@AuthenticationPrincipal User user) {
		var res = user.getAuthorities()
				.stream()
				.filter(auth -> auth.getAuthority().equals("ROLE_ADMIN"))
				.findAny();

		if (res.isPresent()) {
			log.debug("Admin {}, logged in. Redirecting to /admin/", user.getUsername());
			return "redirect:/admin/";
		}
		return "redirect:/user/";
	}

}

