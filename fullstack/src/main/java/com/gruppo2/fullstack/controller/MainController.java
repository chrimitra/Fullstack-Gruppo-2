package com.gruppo2.fullstack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.gruppo2.fullstack.Dao.DomandaDao;
import com.gruppo2.fullstack.Dao.FeedbackDao;
import com.gruppo2.fullstack.Dao.RuoloDao;

import com.gruppo2.fullstack.Dao.UtenteDao;
import com.gruppo2.fullstack.model.ReCaptchaResponse;
import com.gruppo2.fullstack.model.Ruolo;
import com.gruppo2.fullstack.model.Utente;


import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Controller
@RequestMapping("")
public class MainController {

	@Autowired
	UtenteDao UtenteDao;
	@Autowired
	DomandaDao DomandaDao;
	@Autowired
	FeedbackDao FeedbackDao;
	@Autowired
	RuoloDao RuoloDao;
	@Autowired
	RestTemplate restTemplate;
	
	//LOG IN  (sarà uguale sia per admin, sia per utente)
	@GetMapping("/")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String postLogin(@RequestParam("email") String email, 
			@RequestParam("password") String password,
			@RequestParam("g-recaptcha-response") String captchaResponse,
			Model model, HttpSession session) {
		
		
		Utente utente = UtenteDao.login(email, password);
		session.setAttribute("loggedUser", utente);
		/*String url = "https://www.google.com/recaptcha/api/siteverify";
		String params = "?secret=6LcmWycjAAAAAL_CPGuBMw7G9MzzVYRjOYGV0joE&response="+captchaResponse;		
		ReCaptchaResponse reCaptchaResponse = restTemplate.exchange(url+params, HttpMethod.POST,null,ReCaptchaResponse.class).getBody();*/	
		
		if((utente != null) && (utente.getRuolo().getidruolo() == 1)) { //Se è admin
			if(utente.getPassword().equals(password)) {
				return"redirect:/admin/reportAll";
			}
			
		} else if ((utente != null) && (utente.getRuolo().getidruolo() == 2)) { //Se è docente
			if(utente.getPassword().equals(password)) {
				// Return della pagina che avvisa se vuoi cambiare la password da subito
				return "redirect:/insegnante/risultati";
			}
			
		}else if ((utente != null) && (utente.getRuolo().getidruolo() == 3)) { //Se è studente
			if(utente.getPassword().equals(password)) {
			return"redirect:/studente/menuFeedback";
			}
		}
	// SE LE CREDEBZIALI SONO SBAGLIATE MI PRINTA IL MESSAGGIO D'ERRORE
	 return "redirect:/?error"; 
	}
	
	
	
	
	// LOGOUT
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.setAttribute("loggedUtente", null);
		return "redirect:/";
	}
		
	
	@RequestMapping("/error404")
	public String error() {
		return "error404";
	}
	
}

