package com.gruppo2.fullstack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import com.gruppo2.fullstack.Dao.DomandeDao;
import com.gruppo2.fullstack.Dao.FeedbackDao;
import com.gruppo2.fullstack.Dao.RuoliDao;

import com.gruppo2.fullstack.Dao.UserDao;
import com.gruppo2.fullstack.model.User;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class MainController {

	@Autowired
	UserDao UserDao;
	@Autowired
	DomandeDao DomandeDao;
	@Autowired
	FeedbackDao FeedbackDao;
	@Autowired
	RuoliDao RuoliDao;
	
	
	//LOG IN  (sarà uguale sia per admin, sia per utente)
	@GetMapping("/")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String postLogin(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
		User user = UserDao.login(email, password);
		
		if(user != null) {
			// controllare se l'utente ha la password o no
			// se l'utente si registra per la prima volta fare un update nel db e un set sulla password dell'utente
			// se si logga admin va nel menu
			// se si logga l'utente va nel modulo
			return"redirect:/menu";
			
		} else {
			return "redirect:/login";
		}
	}

	
	// REGISTRAZIONE (solo admin)
	@GetMapping("/registrazione")
	public String registrazione() {
		
		return "registrazione";
	}
	
	@RequestMapping(value="/registrazione", method=RequestMethod.POST)
	public String signin(@RequestParam("nome") String name,
							@RequestParam("cognome") String surname,
							@RequestParam("email") String email,
							@RequestParam("password1") String password,
							@RequestParam("password2") String password2){
		
	//verificaMail se è gia esistente
	User verifica = UserDao.verificaMail(email);
	if ((password.equals(password2))&&(verifica == null)){
		User newUser = new User(null,name,surname,email,password);
		UserDao.save(newUser);
		return "redirect:/"; // appena registrato mi porta alla login
	}else {
		// se non ha tutti i requisiti necessari
		System.out.println("male male male");
		return "redirect:/registrazione";}	
	}
	
	
	// MENU (solo admin) 
	@GetMapping("/menu")
	public String menu() {
		return "menu";
	}
	
	
	// REPORT (solo admin)
	@GetMapping("/report")
	public String report() {
		return "report";
	}
	
	
}

