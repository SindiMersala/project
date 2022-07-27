package com.example.project.controller;

import com.example.project.model.request.UserCreateRequest;
import com.example.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/")
@Slf4j
public class LoginController {
    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("register")
    public String createUser(Model model) {
        var user = new UserCreateRequest();
        model.addAttribute("user", user);
        return "user/register";
    }

    @PostMapping("register")
    public String saveUser(
            @Valid @ModelAttribute("user") UserCreateRequest usr,
            BindingResult bindingResult
    ) {
        if(bindingResult.hasErrors()){
            return "user/register";
        }
        else{
            try {
                userService.createUser(usr);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return "redirect:/user/";
        }

    }
}
