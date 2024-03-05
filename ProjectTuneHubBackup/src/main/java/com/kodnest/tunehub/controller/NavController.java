package com.kodnest.tunehub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavController {
	@GetMapping("/registration")
	public String registration() {
		return "registration";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/NewSong")
	public String NewSong() {
		return "NewSong";
	}
	
}
