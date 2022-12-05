package com.gruppo2.fullstack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gruppo2.fullstack.Dao.DomandaDao;
import com.gruppo2.fullstack.Dao.FeedbackDao;
import com.gruppo2.fullstack.Dao.ModuloDao;
import com.gruppo2.fullstack.Dao.RuoloDao;
import com.gruppo2.fullstack.Dao.UtenteDao;
import com.gruppo2.fullstack.model.Feedback;
import com.gruppo2.fullstack.model.Ruolo;
import com.gruppo2.fullstack.model.Utente;


import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

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
	

	@GetMapping("/report") 
	public ModelAndView report(HttpSession session) {
		Utente loggedUser = (Utente) session.getAttribute("loggedUser");
		
		if (loggedUser != null) {
			ModelAndView mavReport = new ModelAndView();
			mavReport.setViewName("report");
			mavReport.addObject("modulo", ModuloDao.findAll());
			mavReport.addObject("utente", loggedUser);
			return mavReport;
		} else {
			ModelAndView mavError = new ModelAndView();
			mavError.setViewName("error404");
			return mavError;
		}
		
	}
	
	@GetMapping("/reportDetails")
	public String reportDetails() {
		return "reportDetails";
	}
	
	
	
	@RequestMapping("/reportDetails/{id}")
	public ModelAndView reportDetails(HttpSession session, @PathVariable("id") Integer id) {
		Utente loggedUser = (Utente) session.getAttribute("loggedUser");
	
		if (loggedUser != null) {
			ModelAndView mavReportDetails = new ModelAndView();
			mavReportDetails.setViewName("/reportDetails");
			mavReportDetails.addObject("modulo", ModuloDao.findByIdmodulo(id));
			mavReportDetails.addObject("feedback", FeedbackDao.dettagli(id));
			mavReportDetails.addObject("domanda", DomandaDao.findAll());
			return mavReportDetails;
			
		} else {
			ModelAndView mavError = new ModelAndView();
			mavError.setViewName("error404");
			return mavError;
		}
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
	

	// Lista Utenti
	@GetMapping("/listaUtenti")
	public String lista(Model model, HttpSession session) {
		
		Utente utente = (Utente) session.getAttribute("loggedUser");
			if(utente != null) {
			model.addAttribute("utenti", UtenteDao.findAll());
			return "listaUtenti";
		} else {
			return "redirect:/error404";
		}
	}
}


















