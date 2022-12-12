package com.gruppo2.fullstack.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.gruppo2.fullstack.Dao.DomandaDao;
import com.gruppo2.fullstack.Dao.FeedbackDao;
import com.gruppo2.fullstack.Dao.InsegnamentoDao;
import com.gruppo2.fullstack.Dao.ModuloDao;
import com.gruppo2.fullstack.Dao.RuoloDao;
import com.gruppo2.fullstack.Dao.UtenteDao;
import com.gruppo2.fullstack.model.Domanda;
import com.gruppo2.fullstack.model.Feedback;
import com.gruppo2.fullstack.model.Insegnamento;
import com.gruppo2.fullstack.model.Modulo;
import com.gruppo2.fullstack.model.ReCaptchaResponse;
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
	@Autowired
	RestTemplate restTemplate;
	
	// REPORT NORMALE
	@GetMapping("/reportAll")
	public String reportDomanda(HttpSession session, Model model) {
		Utente loggedUser = (Utente) session.getAttribute("loggedUser");
		model.addAttribute("utente", loggedUser);
		List <Feedback> feedback = FeedbackDao.feedback();
		model.addAttribute("feedback", feedback);
		
		return "reportAll";
	}
	
	// REPORT FILTRATO ORDINE PER DATA
	@RequestMapping("/reportAll/data")
	public String reportData(HttpSession session, Model model) {
		Utente loggedUser = (Utente) session.getAttribute("loggedUser");
		model.addAttribute("utente", loggedUser);
		List <Feedback> feedback = FeedbackDao.ordineData();
		model.addAttribute("feedback", feedback);
		
		return "reportData";
	}
	
	// REPORT FILTRATO ORDINE PER VOTO
	@RequestMapping("/reportAll/voto")
	public String reportVoto(HttpSession session, Model model) {
		Utente loggedUser = (Utente) session.getAttribute("loggedUser");
		model.addAttribute("utente", loggedUser);
		List <Feedback> feedback = FeedbackDao.ordineVoto();
		model.addAttribute("feedback", feedback);
		
		return "reportVoto";
	}
	
	
	// REPORT FILTRATO ORDINE PER DOMANDA
	@RequestMapping("/reportAll/domanda")
	public String reportPerDomanda(HttpSession session, Model model) {
		Utente loggedUser = (Utente) session.getAttribute("loggedUser");
		model.addAttribute("utente", loggedUser);
		List <Feedback> feedback = FeedbackDao.ordineDomanda();
		model.addAttribute("feedback", feedback);
		
		return "reportDomanda";
	}
	
	
	// FILTRO
	@GetMapping("/filtro")
	public String filtro() {
		return "filtro";
	}
	
	
	
	// REPORT PER MODULO
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
		if (loggedUser != null) {
			return "reportDetails";
		} else {
			return "redirect:/error404";
		}
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
	public ModelAndView registrazione(HttpSession session, Model model) {
		Utente loggedUser = (Utente) session.getAttribute("loggedUser");
		model.addAttribute("utente", loggedUser);
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
		ModelAndView mavError = new ModelAndView();
		mavError.setViewName("error404");
		return mavError;
	}
	
	

	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String signin(@RequestParam("nome") String nome,
							@RequestParam("cognome") String cognome,
							@RequestParam("email") String email,
							@RequestParam("ruolo") String ruolo,
							@RequestParam("modulo")String modulo,
							HttpSession session,
							Model model,
							@RequestParam("g-recaptcha-response") String captchaResponse) { //cambiare html, levando password e mettendo una select con i ruoli
	String url = "https://www.google.com/recaptcha/api/siteverify";
	String params = "?secret=6LcmWycjAAAAAL_CPGuBMw7G9MzzVYRjOYGV0joE&response="+captchaResponse;		
	ReCaptchaResponse reCaptchaResponse = restTemplate.exchange(url+params, HttpMethod.POST,null,ReCaptchaResponse.class).getBody();	
	
	Utente loggedUser = (Utente) session.getAttribute("loggedUser");
	model.addAttribute("utente", loggedUser);
	Ruolo role = (Ruolo) RuoloDao.findByruolo(ruolo);
	Modulo mod = (Modulo) ModuloDao.findBymodulo(modulo);
	
	// GENERA UNA PASSWORD RANDOM
	String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	String lower = "abcdefghijklmnopqrstuvwxyz";
	String num = "0123456789";
	String combination = upper + lower + num;
	int len = 12;
	char[] randomPassword = new char[len];
	Random r = new Random();
	for(int i = 0; i < len; i++) {
		randomPassword[i]=combination.charAt(r.nextInt(combination.length()));
	}
	String password = new String(randomPassword);
	Utente verifica = UtenteDao.verificaMail(email);
	
	// CONTROLLO SE C'E' GIA UN UTENTE
	if (verifica == null){
		Utente newUser = new Utente(null,nome, cognome, email,password, role);
		// CONTROLLO SE IL CAPTCHA E' CHECKATO E SE LA PASSWORD E' LUNGA ALMENO 5 CARATTERI
		System.out.println(role);
		Ruolo ruoloDocente = (Ruolo) RuoloDao.findByidruolo(2);
		// CONTROLLO SE IL RUOLO E' DOCENTE
		if(role.equals(ruoloDocente)) {
			UtenteDao.save(newUser);
			Insegnamento newInsegnamento = new Insegnamento(null, mod, newUser);
			InsegnamentoDao.save(newInsegnamento);
			
		} else {
			UtenteDao.save(newUser);
		}
		return "redirect:registrazione?success"; // appena registrato mi porta alla login
	}else {
		// MI PORTA ALLA PAGINA ERRORE SE NON HA I REQUISITI NECESSARI
		return "redirect:registrazione?error";
	}
	}

	
	
	// LISTA UTENTI
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
	
	
	@RequestMapping("/listaUtenti/modifica")
	public String modificaUtente(HttpSession session, Model model) {
		Utente loggedUser = (Utente) session.getAttribute("loggedUser");
		model.addAttribute("utente", loggedUser);
		System.out.println("modificaUtente");
		if(loggedUser != null) {
			return "modificaUtente";
		} else {
			return "redirect:/error404";
		}
	}
	
	
	// MODIFICA UTENTI
	@RequestMapping("/listaUtenti/modifica/{id}")
	public String modifica(HttpSession session, @PathVariable("id") Integer id, Model model) {
		Utente utenteMod = UtenteDao.singoloUtente(id);
		System.out.println("id");
		Utente loggedUser = (Utente) session.getAttribute("loggedUser");
		model.addAttribute("ruolo", RuoloDao.findAll());
		model.addAttribute("utente", loggedUser);
		model.addAttribute("utenteMod", utenteMod);
            return "modificaUtente";
	}
	
	
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String postModifica(@RequestParam("id") Integer idutente,
			@RequestParam ("nome") String nome,  
			@RequestParam ("cognome") String cognome,
			@RequestParam ("email") String email,
			@RequestParam ("ruolo") String ruolo,
			Model model, HttpSession session) {
		
		Utente utente = UtenteDao.singoloUtente(idutente);
		Ruolo role = (Ruolo) RuoloDao.findByruolo(ruolo);
		Utente loggedUser = (Utente) session.getAttribute("loggedUser");
		model.addAttribute("utente", loggedUser);
		model.addAttribute("ruolo", RuoloDao.findAll());
		utente.setNome(nome);
		utente.setCognome(cognome);
		utente.setEmail(email);
		//utente.setPassword(password);
		utente.setRuolo(role);
		UtenteDao.save(utente);
        return "redirect:/admin/listaUtenti";
	}
	
	
	// RIMUOVI UTENTE
	@RequestMapping("/listaUtenti/rimuovi/{id}")
	public String rimuoviUtente(@PathVariable("id") Integer id) {
		Utente utente = UtenteDao.singoloUtente(id);
		
		//Insegnamento insegnamento = InsegnamentoDao.singoloInsegnamento(id);
		//if(utente.getRuolo().getruolo().equals("insegnante")) {
		//InsegnamentoDao.rimuoviInsegnamento(id);
		UtenteDao.rimuoviUtente(id);
		//} else {
		//	UtenteDao.deleteById(id);
		//}
		
		
		return "redirect:/admin/listaUtenti";
	}
	
	
	

	
	

	
		
		
		
}


















