package com.gruppo2.fullstack.controller;

import java.util.List;

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

import com.gruppo2.fullstack.Dao.UtenteDao;
import com.gruppo2.fullstack.model.Ruolo;
import com.gruppo2.fullstack.model.Utente;

import jakarta.servlet.http.HttpSession;
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
	
	
	//LOG IN  (sarà uguale sia per admin, sia per utente)
	@GetMapping("/")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String postLogin(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
		Utente utente = UtenteDao.login(email, password);
		session.setAttribute("loggedUser", utente);
		
		if((utente != null) && (utente.getRuolo().getidruolo() == 1)) { //Se è admin
			
			// controllare se l'utente ha la password o no
			// se l'utente si registra per la prima volta fare un update nel db e un set sulla password dell'utente
			// se si logga admin va nel menu
			// se si logga l'utente va nel modulo
			return"redirect:/admin/report";
			
		} else if ((utente != null) && (utente.getRuolo().getidruolo() == 2)) { //SE è docente
			return "redirect:/login"; // da cambiare con la pagina "modulo bianco"----------------------------------
		}else if ((utente != null) && (utente.getRuolo().getidruolo() == 3)) { //SE è studente
			
			return"redirect:/studente/menu";
		}
	 return "redirect:/login";
	}

	// LOGOUT
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.setAttribute("loggedUtente", null);
		return "redirect:/";
	}
		
	
	
	
	
}

