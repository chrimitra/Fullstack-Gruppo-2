package com.gruppo2.fullstack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gruppo2.fullstack.Dao.DomandaDao;
import com.gruppo2.fullstack.Dao.FeedbackDao;
import com.gruppo2.fullstack.Dao.RuoloDao;

import com.gruppo2.fullstack.Dao.UserDao;
import com.gruppo2.fullstack.model.User;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class MainController {

	@Autowired
	UserDao UserDao;
	@Autowired
	DomandaDao DomandaDao;
	@Autowired
	FeedbackDao FeedbackDao;
	@Autowired
	RuoloDao RuoloDao;
	
	
	//LOG IN  (sarà uguale sia per admin, sia per utente)
	@GetMapping("/")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String postLogin(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
		User user = UserDao.login(email, password);
		session.setAttribute("loggedUser", user);
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
	public ModelAndView registrazione(HttpSession session) {
		User loggedUser = (User) session.getAttribute("loggedUser");
		System.out.println(loggedUser.getRuolo().idruolo);
		if ((loggedUser.getRuolo().getidruolo() == 1) && (loggedUser != null) ) {
			ModelAndView mavRegistrazione = new ModelAndView();
			mavRegistrazione.setViewName("registrazione");
			mavRegistrazione.addObject("ruolo", RuoloDao.findAll());
			
		return mavRegistrazione;
		} else {
			ModelAndView mavLogin = new ModelAndView();
			mavLogin.setViewName("login");
			return mavLogin;
		}
		
	}
	
	@RequestMapping(value="/registrazione", method=RequestMethod.POST)
	public String signin(@RequestParam("nome") String name,
							@RequestParam("cognome") String surname,
							@RequestParam("email") String email,
							@RequestParam("ruolo") String ruolo) { //cambiare html, levando password e mettendo una select con i ruoli
		
	//verificaMail se è gia esistente
	User verifica = UserDao.verificaMail(email);
	
	if (verifica == null){
		User newUser = new User(null,name,surname,email,ruolo);
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

