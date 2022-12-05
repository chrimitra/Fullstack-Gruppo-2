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

	
	// REGISTRAZIONE (solo admin)
	@GetMapping("/registrazione")
	public ModelAndView registrazione(HttpSession session) {
		Utente loggedUser = (Utente) session.getAttribute("loggedUser");

		if (loggedUser == null){
			ModelAndView mavLogin = new ModelAndView();
			mavLogin.setViewName("login");
			return mavLogin;
		}
		
		else if ((loggedUser.getRuolo().getidruolo() == 1) && (loggedUser != null) ) {
			ModelAndView mavRegistrazione = new ModelAndView();
			mavRegistrazione.setViewName("registrazione");
			mavRegistrazione.addObject("ruolo", RuoloDao.findAll());
			
		return mavRegistrazione;
		} 
		ModelAndView mavLogin = new ModelAndView();//pagina di (errore da sostituire)
		mavLogin.setViewName("login");
		return mavLogin;
	}
	
	
	
	@RequestMapping(value="/registrazione", method=RequestMethod.POST)
	public String signin(@RequestParam("nome") String name,
							@RequestParam("cognome") String surname,
							@RequestParam("email") String email,
							@RequestParam("ruolo") String ruolo) { //cambiare html, levando password e mettendo una select con i ruoli
		
	//verificaMail se è gia esistente
	Ruolo role = (Ruolo) RuoloDao.findByruolo(ruolo);
	String password = "psw";
	Utente verifica = UtenteDao.verificaMail(email);
	
	if (verifica == null){
		Utente newUser = new Utente(null,name,surname, email,password, role);
		
		UtenteDao.save(newUser);
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

