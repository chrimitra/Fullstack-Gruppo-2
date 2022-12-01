package com.gruppo2.fullstack.model;


import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "feedbacks")
public class Feedback {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer idfeedback;
	
	@NotNull  
	public Integer voto;
	
	
	@NotNull  
	public Date data;
	
	@ManyToOne
	@JoinColumn(name="iduser")
	private User utente;
	
	
	@ManyToOne
	@JoinColumn(name="iddomanda")
	private Domanda domanda;


	public Feedback(Integer idfeedback, @NotNull Integer voto, @NotNull Date data, User utente, Domanda domanda) {
		super();
		this.idfeedback = idfeedback;
		this.voto = voto;
		this.data = data;
		this.utente = utente;
		this.domanda = domanda;
	}


	public Integer getidfeedback() {
		return idfeedback;
	}


	public void setidfeedback(Integer idfeedback) {
		this.idfeedback = idfeedback;
	}


	public Integer getVoto() {
		return voto;
	}


	public void setVoto(Integer voto) {
		this.voto = voto;
	}


	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}


	public User getUtente() {
		return utente;
	}


	public void setUtente(User utente) {
		this.utente = utente;
	}


	public Domanda getDomanda() {
		return domanda;
	}


	public void setDomanda(Domanda domanda) {
		this.domanda = domanda;
	}
	

	
	

}
