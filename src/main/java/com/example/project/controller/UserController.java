package com.example.project.controller;

import com.example.project.exception.ResourceAlreadyExistsException;
import com.example.project.model.request.AnswerRequest;
import com.example.project.model.request.BookAppRequest;
import com.example.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

	private static final Logger LOGGER = LogManager.getLogger(UserController.class);

	private final UserService userService;


	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}


	@GetMapping("/")
	public String showAccount(Model model, Principal principal) {
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

	@GetMapping("/create-bookApp")
	public String createBookApp(Model model,Principal principal) {
		var booKApp = new BookAppRequest();
        var vaccines=userService.getAllVaccines();
		var vaccineCenters = userService.getAllVaccineCenters();
		model.addAttribute("bookApp", booKApp);
		model.addAttribute("vaccines", vaccines);
		model.addAttribute("vaccineCenters",vaccineCenters);
		//model.addAttribute("prosAndCons", userService.getVaccineValuesForBookApp(booKApp.getId()));
		return "user/bookApp";
	}

	@PostMapping("/create-bookApp")
	public String createBookApp(
			@Valid @ModelAttribute("bookApp") BookAppRequest bookApp,
			Principal principal,
			BindingResult bindingResult
	) {
		if (bindingResult.hasErrors()) {
			return "user/create-bookApp";
		}
		try {
			userService.createBookApp(bookApp,principal);
			return "redirect:/user/";
		}
		catch(ResourceAlreadyExistsException ex) {
			LOGGER.info(ex.getMessage());
			return "error/400";
		}
	}

	@GetMapping("/create-questionForm")
	public String createQuestionsForm(Model model,Principal principal) {
        var userId=userService.getUserIdByEmail(principal);
		var user=userService.getUserById(userId);
		var answer=new AnswerRequest();
		model.addAttribute("answer", answer);
		model.addAttribute("userIds",userId);
		return "user/questionForm";
	}
	@PostMapping("/create-questionForm")
	public String createBookApp(
			@Valid @ModelAttribute("answer") AnswerRequest answer,
			Principal principal,
			BindingResult bindingResult
	) {
		if (bindingResult.hasErrors()) {
			return "user/create-questionForm";
		}
		try {
			userService.createQuestionForm(answer,principal);
			return "redirect:/user/create-bookApp";
		}
		catch(ResourceAlreadyExistsException ex) {
			LOGGER.info(ex.getMessage());
			return "error/400";
		}
	}


}

