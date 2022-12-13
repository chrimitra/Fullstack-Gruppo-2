package com.gruppo2.fullstack.controller;

import java.util.ArrayList;
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
import com.gruppo2.fullstack.model.Statistiche;
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
		String Materia = ModuloDao.findByIdmodulo(id).modulo;
		if (loggedUser != null) {
			ModelAndView mavReportDetails = new ModelAndView();
			mavReportDetails.setViewName("/reportDetails");
			mavReportDetails.addObject("materia", Materia);
			mavReportDetails.addObject("feedback", FeedbackDao.dettagli(id));
			mavReportDetails.addObject("domanda", DomandaDao.findAll());
			
			List<Statistiche> stats = Statistiche(Materia);
			mavReportDetails.addObject("stats", stats);
			
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
//---------------------------------------------------------
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