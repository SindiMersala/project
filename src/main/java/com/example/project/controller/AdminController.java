package com.example.project.controller;

import  com.example.project.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/admin")
public class AdminController {
	private final AdminService adminService;

	@Autowired
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}

	@GetMapping("/")
	public String viewUser(Model model){
		model.addAttribute("userList", adminService.getAllUsers());
		return "admin/dashboard";
	}

}


