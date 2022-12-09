package com.gruppo2.fullstack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gruppo2.fullstack.Dao.DomandaDao;
import com.gruppo2.fullstack.Dao.FeedbackDao;
import com.gruppo2.fullstack.Dao.ModuloDao;
import com.gruppo2.fullstack.Dao.RuoloDao;
import com.gruppo2.fullstack.Dao.UtenteDao;
import com.gruppo2.fullstack.Dao.InsegnamentoDao;
import com.gruppo2.fullstack.model.Modulo;
import com.gruppo2.fullstack.model.Utente;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/insegnante")
public class InsegnanteController {

	@Autowired
	UtenteDao UtenteDao;
	@Autowired
	DomandaDao DomandaDao;
	@Autowired
	FeedbackDao FeedbackDao;
	@Autowired
	RuoloDao RuoloDao;
	@Autowired
	ModuloDao ModuloDao;
	@Autowired
	InsegnamentoDao InsegnamentoDao;

	//RISULTATI
	@GetMapping("/risultati") 
	public ModelAndView risultati(HttpSession session) {
		Utente loggedUser = (Utente) session.getAttribute("loggedUser");
		String Materia = InsegnamentoDao.Materia(loggedUser.getIdutente());
		
		if (loggedUser != null) {
			ModelAndView mavRisultati = new ModelAndView();
			mavRisultati.setViewName("risultati");
			mavRisultati.addObject("utente", loggedUser);
			mavRisultati.addObject("materia", Materia);
			return mavRisultati;
		}else {
			ModelAndView mavError = new ModelAndView();
			mavError.setViewName("error404");
			return mavError;
		}
	}
	
	
	// PROFILO 
	@RequestMapping("/profilo")
	public String profilo(Model model, HttpSession session) {
		Utente loggedUser = (Utente) session.getAttribute("loggedUser");
		if (loggedUser != null) {
			model.addAttribute(loggedUser);
			return "profilo";
		}else {
			return "redirect:/error404";
		}
	}
	
	// MODIFICA PASSWORD
	@RequestMapping("/modificaPassword")
	public String modificaPassword(HttpSession session, Model model, 
			@RequestParam String nuovaPassword, 
			@RequestParam String confermaPassword) {
		Utente loggedUser = (Utente) session.getAttribute("loggedUser");
		
		if(nuovaPassword.equals(confermaPassword)) {
			model.addAttribute("utente", loggedUser);
			UtenteDao.cambiaPassword(nuovaPassword, loggedUser.idutente);
			return "redirect:/insegnante/profilo";
		} else {
			return "redirect:/error404"; // Messaggio di errore che le password non sono uguali
		}
	}
	
	
}
