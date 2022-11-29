package com.gruppo2.fullstack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gruppo2.fullstack.Dao.UserDao;
import com.gruppo2.fullstack.model.User;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class MainController {

	@Autowired
	private UserDao UserRepository;
	
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
	
//  -------------------- LOG IN ------------------------////
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String postLOGIN(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
		User user = UserRepository.login(email, password);
		if(user == null)
			return "redirect:/index";
		else {
			return"redirect:/ciao";
		}
	}

//---------------------- REGISTRAZIONE ------------------

	@RequestMapping(value="/registrazione", method=RequestMethod.POST)
	public String signin(@RequestParam("nome") String name,
							@RequestParam("cognome") String surname,
							@RequestParam("email") String email,
							@RequestParam("password1") String password,
							@RequestParam("password2") String password2){
	
	User persona = new User(null,name,surname,email,password);
	if (password.equals(password2)){
		UserRepository.save(persona);
		return "redirect:/ciao";
	}else {
		System.out.println("male male male");
		return "redirect:/registrazione";}	
	}
}
