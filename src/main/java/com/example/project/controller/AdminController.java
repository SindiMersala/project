package com.example.project.controller;

import com.example.project.exception.ResourceNotFoundException;
import com.example.project.model.request.NotificationAcceptRequest;
import  com.example.project.service.AdminService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;


@Controller
@RequestMapping("/admin")
public class AdminController {
	private static final Logger LOGGER = LogManager.getLogger(AdminController.class);

	private final AdminService adminService;

	@Autowired
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}

	@GetMapping("/")
	public String showAccount(Model model, Principal principal) {
		var usr = adminService.getUserByPrincipal(principal);
		if (usr.isPresent()) {
			model.addAttribute("user", usr.get());
			model.addAttribute("pending",adminService.hasPendingNotifications(principal));
			return "admin/account";
		}
		return "error/404";
	}


	@GetMapping("/notifications")
	public String invitations( Model model) {
		var notifications = adminService.showNotifications();
		model.addAttribute("notifications", notifications);
		model.addAttribute("req", new NotificationAcceptRequest());
		return "admin/notifications";
	}


	@GetMapping("/notifications/accept/{id}")
	public String acceptRequest(
			@PathVariable long id
	) {
		var out = "redirect:/admin/notifications";
		try {
			adminService.acceptRequest(id);
		}
		catch (ResourceNotFoundException ex) {

			out = "error/404";
		}

		return out;
	}

	@GetMapping("/notifications/reject/{id}")
	public String rejectRequest(
			@PathVariable long id
	) {
		var out = "redirect:/admin/notifications";
		try {
			adminService.rejectRequest(id);
		}
		catch (ResourceNotFoundException ex) {

			out = "error/404";
		}

		return out;
	}
}


