package com.gruppo2.fullstack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class MainController {

	//@Autowired
	//private UserDao UserRepository;
	
	@GetMapping("")
	public String index() {
		return "index";
	}
	
	@GetMapping("/index")
	public String index2() {
		return "index";
	}

	@GetMapping("/registrazione")
	public String registrazione() {
		return "registrazione";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/ciao")
	public String ciao() {
		return "ciao";
	}
	
	
	@GetMapping("/recuperapass")
	public String recuperaPassword() {
		return "recuperapass";
	}
	
	@GetMapping("/confermatoken")
	public String confarmaToken() {
		return "confermatoken";
	}
	
	
	@GetMapping("/profilo")
	public String profilo() {
		return "profilo";
	}
	
}
