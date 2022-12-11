package com.gruppo2.fullstack.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gruppo2.fullstack.Dao.DomandaDao;
import com.gruppo2.fullstack.Dao.FeedbackDao;
import com.gruppo2.fullstack.Dao.RuoloDao;
import com.gruppo2.fullstack.Dao.UtenteDao;
import com.gruppo2.fullstack.model.Domanda;
import com.gruppo2.fullstack.model.Feedback;
import com.gruppo2.fullstack.model.Modulo;
import com.gruppo2.fullstack.model.Utente;
import com.gruppo2.fullstack.risorse.Feedbacks;

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

	
	//pagina menu feedback
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

	
	
	//creazione pagina domande 
	@GetMapping("/sondaggio/{id}")
	public ModelAndView sondaggio(HttpSession session, @PathVariable("id") Integer id, Model model) {
		
		Utente loggedUser = (Utente) session.getAttribute("loggedUser");
		Feedbacks feedbacks = new Feedbacks();	
		Iterable<Domanda> domande = DomandaDao.findAll();	
		
		
		if (loggedUser != null) {
			
		    for (Domanda domanda: domande) {	    	
		    	
		    	Feedback feedback = new Feedback();				 
		    	feedback.setDomanda(domanda);		    	
				feedbacks.addFeeds(feedback);
		    }
		    
		    ModelAndView mavSondaggio = new ModelAndView();
		    mavSondaggio.setViewName("sondaggio");
		    mavSondaggio.addObject("feedbacks", feedbacks);
		    mavSondaggio.addObject("modulo", id);
		    return mavSondaggio;
		    
		} else {
			
			ModelAndView mavError = new ModelAndView();
			mavError.setViewName("error404");
			return mavError;
			
		}
		
	   
		
	}
		

	

	
	
	
	  @RequestMapping(value = "/sondaggio/{id}", method = RequestMethod.POST) 
	  public ModelAndView postSondaggio(HttpSession session, @PathVariable("id") Integer id, @ModelAttribute Feedbacks feedbacks){
		  
		  Iterable<Domanda> domande = DomandaDao.findAll();
		  Utente loggedUser = (Utente) session.getAttribute("loggedUser");
		  Date data = new Date();
		  
		  Integer contatore = 0;
		  
		
		  
		  if (loggedUser != null) {
			  
			  //parte per il salvataggio in simultanea dei feedback
			  for (Domanda domanda: domande) {
				  
				  Feedback feedback = feedbacks.getidFeeds(contatore);
				  feedback.setUtente(loggedUser); 
				  feedback.setData(data);
				  feedback.setModulo(ModuloDao.findByIdmodulo(id));
				  feedback.setDomanda(domanda);
				  
				  FeedbackDao.save(feedback);
				  contatore++;
			  }
			  
			  
			  
			  	//parte per la pagina menufeedback
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
	 
	  
	  


}
