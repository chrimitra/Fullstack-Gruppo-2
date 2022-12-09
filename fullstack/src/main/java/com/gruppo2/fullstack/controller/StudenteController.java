package com.gruppo2.fullstack.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gruppo2.fullstack.Dao.DomandaDao;
import com.gruppo2.fullstack.Dao.FeedbackDao;
import com.gruppo2.fullstack.Dao.RuoloDao;
import com.gruppo2.fullstack.Dao.UtenteDao;

import com.gruppo2.fullstack.model.Modulo;
import com.gruppo2.fullstack.model.Utente;

import jakarta.servlet.http.HttpSession;

import com.gruppo2.fullstack.Dao.ModuloDao;

@Controller
@RequestMapping("/studente")
public class StudenteController {
	
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
	
	/*@GetMapping("/modulo")
	 public String modulo(Model model){		
		Iterable<Modulo> materie = ModuloDao.findAll();		
		model.addAttribute("materie", materie);        
		System.out.println(materie);

        return "menuFeedback";
	}
	
	
	@GetMapping("/{materia}")
	public String sondaggio(@PathVariable String materia){
		System.out.println(materia);
		
		
		return "sondaggio";
	  
	}

	
	@RequestMapping(value="sondaggio/{materia}", method=RequestMethod.POST)
	public String sondaggio(@PathVariable String materia,
			@RequestParam("risposta1") String risposta1,
			@RequestParam("risposta2") String risposta2,
			@RequestParam("risposta3") String risposta3,
			@RequestParam("risposta4") String risposta4, 
			@RequestParam("risposta5") String risposta5,
			Model model, HttpSession session) {
		
		return "menuFeedback";
		
	}*/
	
		
	@GetMapping("/menuFeedback")
  	public ModelAndView menuFeedback(HttpSession session, Model model) {
		Utente loggedUser = (Utente) session.getAttribute("loggedUser");
		
		if (loggedUser != null) {
			ModelAndView mavMenuFeedback = new ModelAndView();
			mavMenuFeedback.setViewName("menuFeedback");
			mavMenuFeedback.addObject("modulo", ModuloDao.findAll());
			mavMenuFeedback.addObject("utente", loggedUser);
			return mavMenuFeedback;
		} else {
			ModelAndView mavError = new ModelAndView();
			mavError.setViewName("error404");
			return mavError;
		}
	}
	
	@GetMapping("/sondaggio")
	public String sondaggio() {
		return "sondaggio";
	}
	
	@RequestMapping("/sondaggio/{id}")
	public ModelAndView sondaggio(HttpSession session, @PathVariable("id") Integer id) {
		Utente loggedUser = (Utente) session.getAttribute("loggedUser");
	
		if (loggedUser != null) {
			ModelAndView mavSondaggio = new ModelAndView();
			mavSondaggio.setViewName("sondaggio");
			mavSondaggio.addObject("modulo", ModuloDao.findByIdmodulo(id));
			mavSondaggio.addObject("domanda", DomandaDao.findAll());
			return mavSondaggio;

		} else {
			ModelAndView mavError = new ModelAndView();
			mavError.setViewName("error404");
			return mavError;
		}
	}
	
	@RequestMapping(value="/sondaggio", method=RequestMethod.POST)
	public String postSondaggio(HttpSession session, @PathVariable("id") Integer id) {
		Utente loggedUser = (Utente) session.getAttribute("loggedUser");
		return "redirect:/error404";
	}
	
	
	
	// POST SONDAGGIO
	
	
	
	

	/* // FeedBack moduli
		
		@GetMapping("/domande")
		public String feedback() {				
					
			Iterable<Domande> domande = DomandeDao.findAll();
			
			ArrayList<String> domandeAL = new ArrayList();
			
			for(Domande dom: domande)
				domandeAL.add(dom.domanda);
			    System.out.println(domandeAL);
					
			
			
			return "domande";
		}

		@RequestMapping(value="/domande", method=RequestMethod.POST)
		public String risposte(@RequestParam("risposta1") String risposta1,
				@RequestParam("risposta2") String risposta2,
				@RequestParam("risposta3") String risposta3,
				@RequestParam("risposta4") String risposta4, 
				@RequestParam("risposta5") String risposta5, Model model, HttpSession session) {
			
			return "feedback";
			
			
		}*/
	
	
	

}
