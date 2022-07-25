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
			model.addAttribute("pending", adminService.hasPendingNotifications(principal));
			return "admin/account";
		}
		return "error/404";
	}


	@GetMapping("/notifications")
	public String invitations(Principal principal, Model model) {
		var notifications = adminService.getNotifications(principal);
		model.addAttribute("invites", notifications);
		model.addAttribute("req", new NotificationAcceptRequest());
		return "admin/invitations";
	}

	@PostMapping("/notifications/accept/{vaccineCenterId}")
	public String acceptInvitation(
			Principal principal,
			@PathVariable long vaccineCenterId
	) {
		var out = "redirect:/user/notifications";
		try {
			adminService.acceptInvitation(principal, vaccineCenterId);
		}
		catch (ResourceNotFoundException ex) {
			LOGGER.info(ex.getMessage());
			out = "error/404";
		}

		return out;
	}
}


