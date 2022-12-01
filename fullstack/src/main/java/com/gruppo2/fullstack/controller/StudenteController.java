package com.gruppo2.fullstack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gruppo2.fullstack.Dao.DomandaDao;
import com.gruppo2.fullstack.Dao.FeedbackDao;
import com.gruppo2.fullstack.Dao.RuoloDao;
import com.gruppo2.fullstack.Dao.UtenteDao;
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
	
	@GetMapping("/modulo")
	public String login() {
		return "modulo";
	}
	

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
