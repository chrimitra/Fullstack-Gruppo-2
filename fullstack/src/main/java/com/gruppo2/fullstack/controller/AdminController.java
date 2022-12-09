package com.gruppo2.fullstack.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

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
import com.gruppo2.fullstack.Dao.InsegnamentoDao;
import com.gruppo2.fullstack.Dao.ModuloDao;
import com.gruppo2.fullstack.Dao.RuoloDao;
import com.gruppo2.fullstack.Dao.UtenteDao;
import com.gruppo2.fullstack.model.Feedback;
import com.gruppo2.fullstack.model.Insegnamento;
import com.gruppo2.fullstack.model.Modulo;
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
	@Autowired
	InsegnamentoDao InsegnamentoDao;
	
	// REPORT
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
	
	// DETTAGLI REPORT
	@GetMapping("/reportDetails")
	public String reportDetails(HttpSession session) {
		Utente loggedUser = (Utente) session.getAttribute("loggedUser");
		return "reportDetails";
	}
	
	
	
	@RequestMapping("/reportDetails/{id}")
	public ModelAndView reportDetails(HttpSession session, @PathVariable("id") Integer id) {
		Utente loggedUser = (Utente) session.getAttribute("loggedUser");
	
		if (loggedUser != null) {
			ModelAndView mavReportDetails = new ModelAndView();
			mavReportDetails.setViewName("/reportDetails");
			mavReportDetails.addObject("modulo", ModuloDao.findByIdmodulo(id));
			//mavReportDetails.addObject("feedback", FeedbackDao.media(id));
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
				mavRegistrazione.addObject("utente", loggedUser);
				mavRegistrazione.addObject("ruolo", RuoloDao.findAll());
				mavRegistrazione.addObject("modulo", ModuloDao.findAll());
				
			return mavRegistrazione;
			} 
			ModelAndView mavError = new ModelAndView();//pagina di (errore da sostituire)
			mavError.setViewName("error404");
			return mavError;
		}
		
		

		@RequestMapping(value="/register", method=RequestMethod.POST)
		public String signin(@RequestParam("nome") String nome,
								@RequestParam("cognome") String cognome,
								@RequestParam("email") String email,
								@RequestParam("ruolo") String ruolo,
								@RequestParam("modulo")String modulo, HttpSession session) { //cambiare html, levando password e mettendo una select con i ruoli
		Utente loggedUser = (Utente) session.getAttribute("loggedUser");	
		//verificaMail se è gia esistente
		Ruolo role = (Ruolo) RuoloDao.findByruolo(ruolo);
		Modulo mod = (Modulo) ModuloDao.findBymodulo(modulo);
		// generare una password random
		String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lower = "abcdefghijklmnopqrstuvwxyz";
		String num = "0123456789";
		//String specialChars = "<>,.?/}{+_-)(*!@#";
		String combination = upper + lower + num;
		int len = 12;
		char[] randomPassword = new char[len];
		Random r = new Random();
		for(int i = 0; i < len; i++) {
			randomPassword[i]=combination.charAt(r.nextInt(combination.length()));
		}
		
		String password = new String(randomPassword);
		
		
		Utente verifica = UtenteDao.verificaMail(email);
		
		if (verifica == null){
			Utente newUser = new Utente(null,nome, cognome, email,password, role);
			UtenteDao.save(newUser);
			Insegnamento newInsegnamento = new Insegnamento(null, mod, newUser);
			InsegnamentoDao.save(newInsegnamento);
			
			return "redirect:registrazione?success"; // appena registrato mi porta alla login
		}else {
			// se non ha tutti i requisiti necessari
			
			return "redirect:/registrazione?error";
		}
		}
	
		
		
		// Lista Utenti
		@RequestMapping("/listaUtenti")
		public String lista(Model model, HttpSession session) {
			Utente loggedUser = (Utente) session.getAttribute("loggedUser");
			
			
				if(loggedUser != null) {
				model.addAttribute("utenti", UtenteDao.listaUtente());
				model.addAttribute(loggedUser);
				
				return "listaUtenti";
			} else {
				return "redirect:/error404";
			}
		}
		
		
		
}


















