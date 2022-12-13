package com.gruppo2.fullstack.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.gruppo2.fullstack.model.Domanda;
import com.gruppo2.fullstack.model.Modulo;
import com.gruppo2.fullstack.model.Statistiche;
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

		//RISULTATI FUnzionante
	@GetMapping("/risultati") 
	public ModelAndView risultati(HttpSession session) {
		Utente loggedUser = (Utente) session.getAttribute("loggedUser");
		String Materia = InsegnamentoDao.Materia(loggedUser.getIdutente());
		
		
		if (loggedUser != null) {
			ModelAndView mavRisultati = new ModelAndView();
			mavRisultati.setViewName("risultati");
			mavRisultati.addObject("utente", loggedUser);
			mavRisultati.addObject("materia", Materia);
//----------------------------------------------------------------
			List<Statistiche> stats = Statistiche(Materia);
			mavRisultati.addObject("stats", stats);
			return mavRisultati;
		}
		else {
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
	
	// MODIFICA PASSWORD(Mappatura)
	@RequestMapping("/modificaPassword")
	public ModelAndView modificaPassword(HttpSession session) {
		Utente loggedUser = (Utente) session.getAttribute("loggedUser");
		if (loggedUser != null) {
			ModelAndView mavMP = new ModelAndView();
			mavMP.setViewName("modificaPassword");
			mavMP.addObject("utente", loggedUser);
			return mavMP;
		}else {
			ModelAndView mavError = new ModelAndView();
			mavError.setViewName("error404");
			return mavError;
		}
	}
	
	
	
	// MODIFICA PASSWORD (post)
	@RequestMapping("/Modifica")
	public String ModificaPassword(HttpSession session, Model model, 
			@RequestParam String nuovaPassword, 
			@RequestParam String confermaPassword) {
		
		Utente loggedUser = (Utente) session.getAttribute("loggedUser");
		
		if(nuovaPassword.equals(confermaPassword)) {
			//model.addAttribute("utente", loggedUser);
			loggedUser.setPassword(confermaPassword);
			UtenteDao.save(loggedUser);
			session.setAttribute("loggedUser", loggedUser);
			return "redirect:/insegnante/profilo";
		} else {
			return "redirect:/error404"; // Messaggio di errore che le password non sono uguali
		}
	}
	
	
//--FUNZIONE STATISTICHE
	
	
	public List<Statistiche> Statistiche(String Materia){
	Double tot1=0.0;
	Double tot2=0.0;
	Double tot3=0.0;
	Double tot4=0.0;
	Double tot5=0.0;
	Double media1=0.0;
	Double media2=0.0;
	Double media3=0.0;
	Double media4=0.0;
	Double media5=0.0;  //domandeMD
	
	List<Double> domande1 = FeedbackDao.Voti(Materia,1);
	List<Double> domande2 = FeedbackDao.Voti(Materia,2);
	List<Double> domande3 = FeedbackDao.Voti(Materia,3);
	List<Double> domande4 = FeedbackDao.Voti(Materia,4);
	List<Double> domande5 = FeedbackDao.Voti(Materia,5);
	
	List <Double> Totali = new ArrayList<>();
	List <Double> Medie= new ArrayList<>();
	
	for(int i = 0; i < domande1.size(); i++) {
//------------------------------------------------------------1
		tot1 += domande1.get(i);
	}
	media1 =tot1/domande1.size();
	Totali.add(tot1);
	Medie.add(media1);
//------------------------------------------------------------2		
	for(int i = 0; i < domande2.size(); i++) {
		tot2 += domande2.get(i);
	}
	media2 =tot2/domande2.size();
	Totali.add(tot2);
	Medie.add(media2);
//------------------------------------------------------------3		
	for(int i = 0; i < domande3.size(); i++) {
		tot3 += domande3.get(i);
	}
	media3 =tot3/domande3.size();
	Totali.add(tot3);
	Medie.add(media3);
//------------------------------------------------------------4		
	for(int i = 0; i < domande4.size(); i++) {
		tot4 += domande4.get(i);
	}
	media4 =tot4/domande4.size();
	Totali.add(tot4);
	Medie.add(media4);
//------------------------------------------------------------5		
	for(int i = 0; i < domande5.size(); i++) {
		tot5 += domande5.get(i);
	}
	media5 = tot5/domande5.size();
	Totali.add(tot5);
	Medie.add(media5);
//------------------------------------------------------------6
	List<Domanda> ListaDomande =DomandaDao.listadomande();
//creazione di una lista di oggette (tipologia Statistiche)
	List<Statistiche> stats = new ArrayList<>();
	for(int i = 0; i < ListaDomande.size(); i++) {
//creazione e aggiunta dell'oggetto statistica alla lista stats
		Statistiche x= new Statistiche(ListaDomande.get(i).domanda,Totali.get(i),Medie.get(i));
		stats.add(x);
	}
	return stats;
	}
	
	
	
	
	
	
}
