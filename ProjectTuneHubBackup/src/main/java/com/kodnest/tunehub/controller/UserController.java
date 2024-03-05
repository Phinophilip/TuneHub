package com.kodnest.tunehub.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kodnest.tunehub.entity.User;
import com.kodnest.tunehub.serviceimpl.UserServiceImpl;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class UserController {
	@Autowired
	UserServiceImpl usi;

	@PostMapping("/register")
	public String addUser(@ModelAttribute User user, Model model) {
		String email = user.getEmail();
		boolean status = usi.emailExists(email);
		if (status == false) {
			usi.addUser(user);
			System.out.println("User Added");
		} else {
			System.out.println("User Already Exists");
		}
		return "Login";
	}

	@PostMapping("/validate")
	public String validateUser(@RequestParam String email, @RequestParam String password,HttpSession session) {
		session.setAttribute("email", email);
		return usi.validateUser(email,password);
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "Login";
	}
}

